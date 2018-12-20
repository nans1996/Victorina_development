using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Registration : MonoBehaviour {

    public InputField login;
    public InputField password;
    public InputField email;
    public InputField firstname;
    public InputField lastname;
    public Text TextMessage;

    //private void Start()
    //{
    //    login.contentType = InputField.ContentType.Alphanumeric;
    //    password.contentType = InputField.ContentType.Password;
    //}

    private void CliRegistration()
    {
        StartCoroutine(Registr());
    }


    private IEnumerator Registr()
    {
        WWWForm form = new WWWForm();
        form.AddField("login", login.text);
        form.AddField("password", password.text);
        form.AddField("email", email.text);
        form.AddField("first_name", firstname.text);
        form.AddField("last_name", lastname.text);
        WWW www = new WWW("http://localhost:8080/users/addUser", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил error " + www.error);
            yield break;
        }
        else {
            if (www.text == "false")
            {
                Debug.Log("Сервер ответил " + www.text);
                TextMessage.text = "*Введите корректные данные";
            }
            else
            SceneManager.LoadScene("main");
        }
    }

}
