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
    public Text timer;
    public GameObject FinishPanel;
    public GameObject ResultPanel;
    public GameObject ResultPanel2;

    public RectTransform prefab;
    public RectTransform content;

    string login = "";
    int k = 1;
    Question[] questions = null;
    Answer[] answer = null;
    string token;
  //  int[] answers_id = new int[30];
    int statistic = 0;
    int questLength = 0;
  Answer[] userAnswers = null;


    public void Start()
    {
       
        string url = "http://localhost:8080/question/get";
        var form = new WWWForm();
        var headers = form.headers; // Заголовки
        //проверяем
        string trim = token.Trim('"');
        //проверка
        Debug.Log("Сохранилось? " + token);
        headers["Authorization"] = trim;

        var www = new WWW(url, null, headers);
        StartCoroutine(GetQuestions(www));
    }

    private void OnEnable()
    {
        token = PlayerPrefs.GetString("tokenUser");
        login = PlayerPrefs.GetString("Username");
    }

    //переход на сцену с результатом
    //public void ClickResult()
    //{
    //    string url = "http://localhost:8080/question/get";
    //    var form = new WWWForm();
    //    var headers = form.headers; // Заголовки
    //    //проверяем
    //    string trim = token.Trim('"');
    //    headers["Authorization"] = trim;

    //    var www = new WWW(url, null, headers);
    //    StartCoroutine(GetQuestions(www));

    //}


    public void ClickResults()
    {
        textAmount.text = "";
        timer.text = "";
        if ((!ResultPanel.GetComponent<Animator>().enabled) && (!ResultPanel2.GetComponent<Animator>().enabled))
        {
            ResultPanel.GetComponent<Animator>().enabled = true;
            ResultPanel2.GetComponent<Animator>().enabled = true;
            Debug.Log("Здееесь заход на вывод");
            OnReciveModels();
        }
        else
        {
            ResultPanel.GetComponent<Animator>().SetTrigger("InResult");
            ResultPanel2.GetComponent<Animator>().SetTrigger("TriggerPanelTop");
            //OnReciveModels();
          
        }
    }



    public  void OnReciveModels()
      {
        Debug.Log("Зашло в метод");
        foreach (Transform child in content)
        {
            Destroy(child.gameObject);
        }
        for (int i=0; i < userAnswers.Length; i++)
        { 
            Debug.Log(userAnswers[i].id_answer +"  "+ userAnswers[i].id_question);
            //
            var instance = GameObject.Instantiate(prefab.gameObject) as GameObject;
            instance.transform.SetParent(content, false);
            InitializeItemView(instance, userAnswers[i], i);
        }
    }

    void InitializeItemView(GameObject viewGameObject, Answer ua, int i)
    {
        ResultView view = new ResultView(viewGameObject.transform);
        view.question.text = questions[i].description;
        if (ua.result)
        {
            view.answer_res.text = "1";
            statistic++;
        }
        else
            view.answer_res.text = "0";

        Debug.Log("типа статистика "+statistic);

    }

    //IEnumerator GetItems(WWW www, System.Action<Statistic[]> callback)
    //{
    //    yield return www;
    //    var result = new Statistic[0];

    //    if (www.error == null)
    //    {
    //        Statistic[] mList = JSonHelper.getJsonArray<Statistic>(www.text);
    //        Debug.Log("Успешно " + www.text);
    //        callback(mList);
    //    }
    //    else
    //    {
    //        Debug.Log("Ошибка " + www.text);
    //        callback(result);
    //    }

    //}

    public class ResultView
    {
        public Text question;
        public Text answer_res;

        public ResultView(Transform rootView)
        {
            question = rootView.Find("Text").GetComponent<Text>();
            answer_res = rootView.Find("Text (1)").GetComponent<Text>();
        }

        
    }

    //*******************************************************************

    //вывод вопросов
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

    //вывод ответа по вопросу
    public void StartAnswer(int id)
    {
        string url = "http://localhost:8080/answer/getByIdQuestion?id_question=" + id;
        var form = new WWWForm();
        var headers = form.headers; // Заголовки
         string trim = token.Trim('"');
        headers["Authorization"] = trim;
        WWW www2 = new WWW(url, null, headers);
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
        userAnswers = new Answer[questions.Length-1];
        int i = 0;
 
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
        answer4.GetComponentInChildren<Text>().text = answer[3].description;

    }


    private IEnumerator WriteAnswer(int id_answer)
    {
        var form = new WWWForm();
        form.AddField("login", login);
        form.AddField("id_answer", id_answer);
        string url = "http://localhost:8080/answer/add";
        var data = form.data; // Данные в byte[]
        var headers = form.headers; // Заголовки
                                    
        string trim = token.Trim('"');
        headers["Authorization"] = trim;
        Debug.Log("ЗАПИСЬ " + data);
        WWW www = new WWW(url, data, headers);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил error " + www.error);
            yield break;
        }
        Debug.Log("Сервер ответил " + www.text);

    }

//запись на сервер
    //public void ClickExiteButton()
    //{
    //    for(int i=0; i<answers_id.Length; i++)
    //    {
    //        StartCoroutine(WriteAnswer(answers_id[i]));
    //    }
    //}


    //    public void ToMass(Answer answer, int i, int id)
    //{
    //    Debug.Log(id + "  "+ answer.id_answer);
    //    UserAnswer ua = new UserAnswer(id, answer.id_answer);
    //    userAnswers[i] = ua;

    //    if (answer.result)
    //    {
    //        statistic++;
    //    }
    //    Debug.Log("Из массива " + userAnswers[i].id_answer + "  "+ userAnswers[i].id_question +"  " + answer.id_answer + "  "+i);
    //}


    public void Click()
    {
        

        int i = k;
        if ((questions.Length >= i+1) && (answer1.isOn || answer2.isOn || answer3.isOn || answer4.isOn))
        {
            textQuestion.text = questions[i].description;
            textAmount.text = (k + 1) + "/35";
            k++;
            StartAnswer(questions[i].idQuestion);
          
            //идет запись ответов
            Debug.Log("идет запись вопросов");
            ResultToService(i - 1);



        }


        
            if ((!FinishPanel.GetComponent<Animator>().enabled) && (questions.Length == i ))
            {
                StartCoroutine(WriteStatistic());

                FinishPanel.GetComponent<Animator>().enabled = true;
            }
            else
            {
                StartCoroutine(WriteStatistic());
                FinishPanel.GetComponent<Animator>().SetTrigger("In");
            }
        
          NextQuestion();
    }

    public void ResultToService(int i)
    {
        Debug.Log("ResultToService i "+i);
        if (answer1.isOn)
        {
            userAnswers[i] = answer[0];
        }
        else
            if (answer2.isOn)
        {
            userAnswers[i] = answer[1];
        }
        else
            if (answer3.isOn)
        {
            userAnswers[i] = answer[2];
        }
        else
            if (answer4.isOn)
        {
            userAnswers[i] = answer[3];
        }
    }


    private IEnumerator WriteStatistic()
    {
        var form = new WWWForm();
        form.AddField("login", login);
        form.AddField("count_truth", statistic);
        string url = "http://localhost:8080/statistic/add";
        var data = form.data; // Данные в byte[]
        var headers = form.headers; // Заголовки
                                    //тянем токен
                                    //Authorization.Repos r = new Authorization.Repos();
                                    //string str2 = r.R;
        string trim = token.Trim('"');
        headers["Authorization"] = trim;
        Debug.Log("ЗАПИСЬ " + data);
        WWW www = new WWW(url, data, headers);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил error " + www.error);
            yield break;
        }
        Debug.Log("Сервер ответил " + www.text);

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
        public int id_question;
        public bool result;
    }

    [System.Serializable]
    public class UserAnswer
    {
        public int id_question;
        public int id_answer;

        public UserAnswer(int id_q, int id_a)
        {
            this.id_question = id_q;
            this.id_answer = id_a;
        }
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
