function connexion (formulaire)
{
	var login=formulaire.login.value;
	var password=formulaire.password.value;
	var ok=verif_formulaire_connexion(login,password);
	if (ok)
		connecte(login,password);
}

function verif_formulaire_connexion(login,password)
{
	if (login.length==0)
	{
		func_erreur("Login obligatoire");
		return false;
	}
	if (password.length==0)
	{
		func_erreur("Password obligatoire")
		return false;
	}
	return true;
}

function connecte(login,password)
{
	console.log("connection de " + login + ", password: " + password);
	var url = "Login";
	$.ajax({
		type:"GET",
		url: "Login",
		data:"login="+login+"&password="+password,
		datatype: "JSON",
		success: function(output)
		{
			responseConnexion(output);
		},
		error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
	});

}

function responseConnexion(output)
{
	if(output.Error!=undefined)
		func_erreur(output.Error);
	if (output.Error==undefined)
	{
		env.key=output.key;
		env.id=output.id;
		env.login=output.login;
		if (output.follows==undefined)
			env.follows=new Set();
		else
			env.follows=output.follows;
		makeProfilPanel(output.id,output.login);
	}
}


 