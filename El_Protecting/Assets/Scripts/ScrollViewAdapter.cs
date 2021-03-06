﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class ScrollViewAdapter : MonoBehaviour {

    public RectTransform prefab;
    public RectTransform content;

    string token;
    string Username;
  //  string str = @"[{""count_truth"": 30, ""date"":23.12.2018}, {""count_truth"": 30, ""date"":23.12.2018}]";

    private void Start()
    {
       

        string url = "http://localhost:8080/statistic/getByIdUser?login="+Username;
        var form = new WWWForm();
        var headers = form.headers; 
        string trim = token.Trim('"');
        headers["Authorization"] = trim;
        WWW www = new WWW(url, null, headers);
        StartCoroutine(GetItems(www, result => OnReciveModels(result)));
    }

    private void OnEnable()
    {
        token = PlayerPrefs.GetString("tokenUser");
        Username = PlayerPrefs.GetString("Username");
    }

    void OnReciveModels(Statistic[] statistics)
    {
       

        foreach (Transform child in content)
        {
            Destroy(child.gameObject);
        }

 var instance1 = GameObject.Instantiate(prefab.gameObject) as GameObject;
        instance1.transform.SetParent(content, false);
        StatisticView view = new StatisticView(instance1.transform);
        view.date.text = "Дата";
        view.count.text = "Результат";

        foreach(var statistic in statistics)
        {
            var instance = GameObject.Instantiate(prefab.gameObject) as GameObject;
            instance.transform.SetParent(content, false);
            InitializeItemView(instance, statistic);
        }
    }

    void InitializeItemView(GameObject viewGameObject, Statistic statistic)
    {
        StatisticView view = new StatisticView(viewGameObject.transform);
        view.date.text = statistic.date;
        view.count.text = statistic.count_truth.ToString()+"/35";
    }

    IEnumerator GetItems(WWW www, System.Action<Statistic[]> callback)
    {
        yield return www;
        var result = new Statistic[0];

        if (www.error == null)
        {
            Statistic[] mList = JSonHelper.getJsonArray<Statistic>(www.text);
            Debug.Log("Успешно " + www.text);
          callback(mList);
        }
        else
        {
            Debug.Log("Ошибка " + www.text);
          //  SceneManager.LoadScene("error");
            callback(result);
        }
 
    }

    public class StatisticView
    {
        public Text date;
        public Text count;

        public StatisticView(Transform rootView)
        {
            date = rootView.Find("Text").GetComponent<Text>();
            count = rootView.Find("Text (1)").GetComponent<Text>();
        }
    }

    [System.Serializable]
    public class Statistic
    {
 
        public string date;
        public int count_truth;

    }

    public class JSonHelper
    {
        public static T[] getJsonArray<T>(string json)
        {
            string newJson = "{\"array\":"+json+"}";
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
