using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class QuestionScript : MonoBehaviour {

    public  Text textQuestion;
    public Toggle answer1;
    public Toggle answer2;
    public Toggle answer3;
    public Toggle answer4;
    public Text textAmount;

    int j = 1;

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
        string url = "http://localhost:8080/answer/getByIdQuestion?id_question="+id;
        WWW www = new WWW(url);
        StartCoroutine(GetAnswer(www));
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


    public  void GetQ(string json)
    {
        Question[] questions = JSonHelper.getJsonArray<Question>(json);
            int i = j - 1;
        Debug.Log("вопрос "+ questions[0].description);
          textQuestion.text = questions[i].description;
            textAmount.text = j + "/35";
            j++;
            StartAnswer(questions[i].idQuestion);
        
    }


    public void GetA(string json)
    {
        Answer[] answer = JSonHelper.getJsonArray<Answer>(json);
        answer1.GetComponentInChildren<Text>().text = answer[0].description;
        answer2.GetComponentInChildren<Text>().text = answer[1].description;
        answer3.GetComponentInChildren<Text>().text = answer[2].description;
      //  answer4.GetComponentInChildren<Text>().text = answer[3].description;

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
