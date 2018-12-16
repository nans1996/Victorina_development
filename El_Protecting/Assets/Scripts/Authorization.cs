using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public class Authorization : MonoBehaviour {


    public InputField log;
    
    public InputField pass;
    public Text message;
    public Toggle flag;

    string token = "";
    string Userlogin = "";

    //public void Start()
    //{
    //    log.contentType = InputField.ContentType.Alphanumeric;
    //    pass.contentType = InputField.ContentType.Password;
    //}

 

    //public void Toggle_change(bool value)
    //{
    //    if (value)
    //    {
    //        pass.contentType = InputField.ContentType.Standard;

    //    }
    //    else
    //    {
    //        pass.contentType = InputField.ContentType.Password;
    //    }
    //}

    //авторизация
    public void CliAuthorization()
       {
            StartCoroutine(Login(result => Callback(result)));

    }

    public IEnumerator Login(System.Action<string> cd)
    {
        WWWForm form = new WWWForm();
        form.AddField("login", log.text);
        form.AddField("password", pass.text);

        WWW www = new WWW("http://localhost:8080/users/logIn", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил нет такого пользователя " + www.error);
            Debug.Log(www.error);
            message.text = "Неверный логин или пароль";
            yield break;
        }

        Debug.Log("Пользователь авторизован " + www.text);
        Repos r = new Repos();
        r.Save(www.text);
        //проверки
        Debug.Log("ПРОверка "+ r.R);
        token = r.R;
        Debug.Log("Прроверка 2 "+ token);
        cd(token);

    }


    public void Callback(string result)
    {
        StartCoroutine(GetUsername(result));
    }


    public IEnumerator GetUsername(string token)
    {
        var form = new WWWForm();
        //var data = form.data; // Данные в byte[]
        var headers = form.headers; // Заголовки
        string trim = token.Trim('"');
        headers["Authorization"] = trim;
        var www = new WWW("http://localhost:8080/users/edit2", null, headers);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил нет логина " + www.error);

            yield break;
        }

        Debug.Log("ЕСТЬ! " + www.text);
        User us = FromJson(www.text);
        Userlogin = us.username;
        Debug.Log("Типа логин "+Userlogin);
        //переход
        SceneManager.LoadScene("welcome");
       

    }


    public static User FromJson(string json)
    {
        User user = JsonUtility.FromJson<User>(json);
        return user;
    }

    [System.Serializable]
    public class User
    {
        public string username;

    }

    private void OnDisable()
    {
        PlayerPrefs.SetString("tokenUser", token);
        Debug.Log("Disable  " + token);
        PlayerPrefs.SetString("Username", Userlogin);
        Debug.Log("Username  " + Userlogin);

    }


    [System.Serializable]
    public class Repos
    {
        public string R { get; set; }
        public void Save(string str)
        {
            R = str;
        }

  
    }


}

