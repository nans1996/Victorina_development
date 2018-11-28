using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ScrollViewAdapter : MonoBehaviour {

    public RectTransform prefab;
    public RectTransform content;


    private void Start()
    {
        string url = "http://localhost:8080/users/ge";
        WWW www = new WWW(url);

        StartCoroutine(GetItems(www, result => OnReciveModels(result)));
    }

    void OnReciveModels(Statistic[] statistics)
    {
        foreach(Transform child in content)
        {
            Destroy(child.gameObject);
        }
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
        view.count.text = statistic.count;
    }

    IEnumerator GetItems(WWW www, System.Action<Statistic[]> callback)
    {
        yield return www;
        var result = new Statistic[0];

        if (www.error == null)
        {
            Debug.Log("Успешно " + www.text);
            callback(result);
        }
        else
        {
            Debug.Log("Ошибка " + www.text);
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


    public class Statistic
    {
        public string date;
        public string count;
    }
	
}
