using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TakeUsername : MonoBehaviour {

    string Username = "";
    string token = "";

    private void OnEnable()
    {
        token = PlayerPrefs.GetString("tokenUser");
    }

  public string Returnlogin()
    {
        StartCoroutine(GetLogin());
        User u = new User();
        return u.username;
    }


    public IEnumerator GetLogin()
    {
        WWWForm form = new WWWForm();
        var headers = form.headers; // Заголовки
        Debug.Log("токен для логина " + token);
        string trim = token.Trim('"');
        var www = new WWW("http://localhost:8080/users/edit2", null, headers);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил нет данных пользователя " + www.error);
            yield break;
        }

        Debug.Log("Пользователь данные " + www.text);
        User us = FromJson(www.text);
        us.Save(us.username);

    }

  

    public static User FromJson(string json)
    {
        User user = JsonUtility.FromJson<User>(json);
        return user;
    }

    [System.Serializable]
    public class User
    {
        public string username { get; set; }
        public void Save(string str)
        {
            username = str;
        }
    }
}
