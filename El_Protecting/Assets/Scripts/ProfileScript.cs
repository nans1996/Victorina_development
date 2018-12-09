using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ProfileScript : MonoBehaviour {

    public InputField login;
    public InputField password;
    public InputField email;
    public InputField first_name;
    public InputField last_name;

    string log = "nasty.rod@yandex.ru";

    private void Start()
    {
        string url = "http://localhost:8080/users/getByLogin?login="+log;
        WWW www = new WWW(url);
        StartCoroutine(GetItems(www));
    }


    IEnumerator GetItems(WWW www)
    {
        yield return www;
      //  var result = new User[0];

        if (www.error == null)
        {
          Debug.Log("Успешно " + www.text);
          User us = FromJson(www.text);
            login.text = us.login;
            password.text = us.password;
            email.text = us.email;
            first_name.text = us.first_name;
            last_name.text = us.last_name;
        }
        else
        {
            Debug.Log("Ошибка " + www.error);
          
        }

    }


    public void Edite()
    {
        StartCoroutine(EditUser());
    }


    private IEnumerator EditUser()
    {
        WWWForm form = new WWWForm();
        form.AddField("login", login.text);
        form.AddField("password", password.text);
        form.AddField("email", email.text);
        form.AddField("first_name", first_name.text);
        form.AddField("last_name", last_name.text);
        WWW www = new WWW("http://localhost:8080/users/edit", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил error " + www.error);
            yield break;
        }
        Debug.Log("Сервер ответил " + www.text);
       
    }

    public static User FromJson(string json)
    {
        User user = JsonUtility.FromJson<User>(json);
        return user;
    }

    [System.Serializable]
    public class User
    {
        public string login;
        public string password;
        public string email;
        public string first_name;
        public string last_name;

    }

   
}
