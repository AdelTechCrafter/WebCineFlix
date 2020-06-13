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
	//Avec les servlets
	//login : 3408748 mdp: monmdp
	console.log("connection de " + login + ", password: " + password);
	var url = "Login";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url: "Login",
			data:"login="+login+"&password="+password,
			datatype: "JSON",
			success: function(rep)
			{
				responseConnexion(rep);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	//En Local
	else
	{
		bool=false;
		i=0
		while (i<localusers.length)
		{
			if ((localusers[i].login==login) && (localusers[i].password==password))
			{
				bool=true;
				break;
			}
			i=i+1;
		}
		if (bool)
		{
			var key="ABCD";
			responseConnexion({"key":key,"id":localusers[i].id,"login":login,"follows":follows[localusers[i].id]});
		}
		else
			func_erreur("Login ou password incorrect");
	}
}

function responseConnexion(rep)
{	
	//Local
	if (noConnection)
	{
		if (rep.Error==undefined)
		{
			env.key=rep.key;
			env.id=rep.id;
			env.login=rep.login;
			if (rep.follows==undefined)
				env.follows=new Set();
			else
				env.follows=rep.follows;
			//for (var i=0;i<rep.follows.length;i++)
				//env.follows.add(rep.follows[i]);
			//alert(env.follows[1]);
		}
		/*
		else
		{
			follows[rep.id]=new Set();
			for (var i=0;i<rep.follows.length;i++)
				follows[rep.id].add(rep.follows[i]);
		}*/
		makeProfilPanel(rep.id,rep.login);
	}
	//Connecté
	else
	{
		//Connecté
		if(rep.Error!=undefined)
			func_erreur(rep.Error);
		if (rep.Error==undefined)
		{
			env.key=rep.key;
			env.id=rep.id;
			env.login=repp.login;
			if (rep.Follows==undefined)
				env.follows=new Set();
			else
				env.follows=rep.Follows;
			makeProfilPanel(rep.id,rep.login);
			//for (var i=0;i<rep.follows.length;i++)
				//env.follows.add(rep.follows[i]);
			//alert(env.follows[1]);
		}
	}
}

 