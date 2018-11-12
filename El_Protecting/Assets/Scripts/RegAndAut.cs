using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RegAndAut : MonoBehaviour {

    public string login;
    public string password;
    public string email;
    public string firstname;
    public string lastname;

   

    // Use this for initialization
    private void Start () {

      // StartCoroutine(Registration());
        StartCoroutine(GetAllUsers());
        StartCoroutine(EditUser());
        StartCoroutine(GetAllUsers());


    }
	
    //регистрация
	private IEnumerator Registration()
    {
        WWWForm form = new WWWForm();
        form.AddField("login", login);
        form.AddField("password", password);
        form.AddField("email", email);
        form.AddField("first_name", firstname);
        form.AddField("last_name", lastname);
        WWW www = new WWW("http://localhost:8080/users/add", form);
        yield return www;
        Debug.Log("Сервер ответил " + www.text);
    }

    //вывести всех
    private IEnumerator GetAllUsers()
    {
        WWW www = new WWW("http://localhost:8080/users/get");
        yield return www;
        Debug.Log("Get, Сервер ответил " + www.text);
    }

    //вывести всех
    private IEnumerator GetUser()
    {
        WWWForm form = new WWWForm();
        form.AddField("id", 14);
        WWW www = new WWW("http://localhost:8080/users/findById", form);
        yield return www;
        Debug.Log("GetUser, Сервер ответил " + www.text);
    }

    //обновить
    private IEnumerator EditUser()
    {
        WWWForm form = new WWWForm();
        form.AddField("id", 30);
        form.AddField("login", login);
        form.AddField("password", password);
        form.AddField("email", email);
        form.AddField("first_name", firstname);
        form.AddField("last_name", lastname);
        WWW www = new WWW("http://localhost:8080/users/edit", form);
        yield return www;
        Debug.Log("EditeUser, Сервер ответил " + www.text);
    }
}
