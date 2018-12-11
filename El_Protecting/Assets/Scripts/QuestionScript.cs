using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class QuestionScript : MonoBehaviour
{

    public Text textQuestion;
    public Toggle answer1;
    public Toggle answer2;
    public Toggle answer3;
    public Toggle answer4;
    public Text textAmount;

    int id_user = 1;
    int k = 1;
    Question[] questions = null;
    Answer[] answer = null;

    public void Start()
    {
        string url = "http://localhost:8080/question/get";
        WWW www = new WWW(url);
        StartCoroutine(GetQuestions(www));
    }


    IEnumerator GetQuestions(WWW www)
    {
        yield return www;
        if (www.error == null)
        {
            Debug.Log("Успешно " + www.text);
            GetQ(www.text);
        }
        else
        {
            Debug.Log("Ошибка " + www.error);

        }

    }


    public void StartAnswer(int id)
    {
        string url = "http://localhost:8080/answer/getByIdQuestion?id_question=" + id;
        WWW www2 = new WWW(url);
        StartCoroutine(GetAnswer(www2));
    }

    IEnumerator GetAnswer(WWW www)
    {
        yield return www;
        if (www.error == null)
        {
            Debug.Log("Успешно " + www.text);
            GetA(www.text);
        }
        else
        {
            Debug.Log("Ошибка " + www.error);

        }

    }


    public void GetQ(string json)
    {
        questions = JSonHelper.getJsonArray<Question>(json);
        int i = 0;
        Debug.Log("вопрос " + questions[i].description);
        textQuestion.text = questions[i].description;
        textAmount.text = "1/35";
        StartAnswer(questions[i].idQuestion);

    }

    public void NextQuestion()
    {
       
        answer1.isOn = false;
        answer2.isOn = false;
        answer3.isOn = false;
        answer4.isOn = false;
    }

    public void GetA(string json)
    {
        answer = JSonHelper.getJsonArray<Answer>(json);
        answer1.GetComponentInChildren<Text>().text = answer[0].description;
        answer2.GetComponentInChildren<Text>().text = answer[1].description;
        answer3.GetComponentInChildren<Text>().text = answer[2].description;
        //answer4.GetComponentInChildren<Text>().text = answer[3].description;

    }


    private IEnumerator WriteAnswer(int id_user, int id_answer)
    {
        WWWForm form = new WWWForm();
        form.AddField("id_user", id_user);
        form.AddField("id_answer", id_answer);
        WWW www = new WWW("http://localhost:8080/user_answer/add", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил error " + www.error);
            yield break;
        }
        Debug.Log("Сервер ответил " + www.text);

    }


    public void Click()
    {
        NextQuestion();

        if (answer1.isOn || answer2.isOn || answer3.isOn || answer4.isOn)
        {
            int i = k;
            Debug.Log("вопрос " + questions[i].description);
            textQuestion.text = questions[i].description;
            textAmount.text = (k + 1) + "/35";
            k++;
            StartAnswer(questions[i].idQuestion);
            ResultToService();

        }

    }

    public void ResultToService()
    {
        if (answer1.isOn)
        {
            StartCoroutine(WriteAnswer(id_user, answer[0].id_answer));
        }
        else
            if (answer2.isOn)
        {
            StartCoroutine(WriteAnswer(id_user, answer[1].id_answer));
        }
        else
            if (answer3.isOn)
        {
            StartCoroutine(WriteAnswer(id_user, answer[2].id_answer));
        }
        else
            if (answer4.isOn)
        {
            StartCoroutine(WriteAnswer(id_user, answer[3].id_answer));
        }
    }


    [System.Serializable]
    public class Question
    {
        public int idQuestion;
        public string description;
    }

    [System.Serializable]
    public class Answer
    {
        public int id_answer;
        public string description;
        public bool result;
    }

    public class JSonHelper
    {
        public static T[] getJsonArray<T>(string json)
        {
            string newJson = "{\"array\":" + json + "}";
            Wrapper<T> wrapper = JsonUtility.FromJson<Wrapper<T>>(newJson);
            return wrapper.array;

        }

        public static string arrayToJson<T>(T[] array)
        {
            Wrapper<T> wrapper = new Wrapper<T>();
            wrapper.array = array;
            return JsonUtility.ToJson(wrapper);
        }

        private class Wrapper<T>
        {
            public T[] array;
        }
    }

}
