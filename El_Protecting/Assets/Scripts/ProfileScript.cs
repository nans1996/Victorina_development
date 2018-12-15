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
  //  string str = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXN0eS5yb2RAeWFuZGV4LnJ1IiwiaWF0IjoxNTQ0ODY2ODEwLCJleHAiOjE1NDQ4NzA0MTB9.jBIK9SVFc6QywcH6DYy3BQJUts7rQE5ZRPW2m29P8FU";

    string token;

    private void Start()
    {

        login.contentType = InputField.ContentType.Alphanumeric;
        password.characterLimit = 20;
        email.contentType = InputField.ContentType.EmailAddress;

        string url = "http://localhost:8080/users/getByLogin";
        var form = new WWWForm();
        form.AddField("login", log);

        var data = form.data; // Данные в byte[]
        var headers = form.headers; // Заголовки
        string trim = token.Trim('"');
        headers["Authorization"] = trim;
        var www = new WWW(url, data, headers);
        StartCoroutine(GetItems(www));
    }


    private void OnEnable()
    {
        token = PlayerPrefs.GetString("tokenUser");
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
        var form = new WWWForm();
        form.AddField("login", log);
        form.AddField("password", password.text);
        form.AddField("email", email.text);
        form.AddField("first_name", first_name.text);
        form.AddField("last_name", last_name.text);
        var data = form.data; // Данные в byte[]
        var headers = form.headers; // Заголовки
        //Authorization.Repos r = new Authorization.Repos();
        //string str2 = r.R;
         string trim = token.Trim('"');
        headers["Authorization"] = trim;
        var www = new WWW("http://localhost:8080/users/edit", data, headers);
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
