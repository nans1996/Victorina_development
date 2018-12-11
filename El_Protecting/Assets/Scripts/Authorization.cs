using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Authorization : MonoBehaviour {


    public InputField log;
    
    public InputField pass;
    public Text message;
    public Toggle flag;


    public void Start()
    {
        log.contentType = InputField.ContentType.Alphanumeric;
        pass.contentType = InputField.ContentType.Password;
    }

 

    public void Toggle_change(bool value)
    {
        if (value)
        {
            pass.contentType = InputField.ContentType.Standard;

        }
        else
        {
            pass.contentType = InputField.ContentType.Password;
        }
    }

    //авторизация
    public void CliAuthorization()
        {
            StartCoroutine(Login());
        }

    public IEnumerator Login()
    {
        WWWForm form = new WWWForm();
        form.AddField("login", log.text);
        form.AddField("password", pass.text);

        WWW www = new WWW("http://localhost:8080/users/authorization", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил нет такого пользователя " + www.error);
            message.text = "Неверный логин или пароль";
            yield break;
        }

        Debug.Log("Пользователь авторизован " + www.text);
        User u = new User();
        u.Save(www.text);
        SceneManager.LoadScene("welcome");
    }


    [System.Serializable]
    public class User
    {
        public string user;

        public User()
        {
            user = "";

        }

        public void Save(string str)
        {
            user = str;
        }

        public void Destroy()
        {
            user = "";
        }
    }


    }

