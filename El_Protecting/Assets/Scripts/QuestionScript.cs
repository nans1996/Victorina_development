using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class QuestionScript : MonoBehaviour {

    public Text textQuestion;
    public Toggle answer1;
    public Toggle answer2;
    public Toggle answer3;
    public Toggle answer4;
    public Text textAmount;

    int k = 1;
    Question[] questions;

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
       // NextQuestion();
    }

    public void NextQuestion()
    {
        var instance = GameObject.Find("Toggle1") as GameObject;
       // instance.transform.SetParent(content, false);
        answer1 = instance.GetComponent<Toggle>();
        answer2.isOn = false;
        answer3.isOn = false;
        answer4.isOn = false;
    }

    public void Click()
    {
         NextQuestion();

        //if (answer1.isOn || answer2.isOn || answer3.isOn || answer4.isOn)
        //{
        //    int i = k;
        //    Debug.Log("вопрос " + questions[i].description);
        //    textQuestion.text = questions[i].description;
        //    textAmount.text = (k + 1) + "/35";
        //    k++;
        //    StartAnswer(questions[i].idQuestion);
            
        //}
        //NextQuestion();
    }

    public void GetA(string json)
    {
        Answer[] answer = JSonHelper.getJsonArray<Answer>(json);
        answer1.GetComponentInChildren<Text>().text = answer[0].description;
        answer2.GetComponentInChildren<Text>().text = answer[1].description;
        answer3.GetComponentInChildren<Text>().text = answer[2].description;
      //answer4.GetComponentInChildren<Text>().text = answer[3].description;

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
