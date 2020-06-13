/**
 * 
 */
function Enregistrement(formulaire){
	var login= formulaire.login.value;
	var password=formulaire.password.value;
	var nom=formulaire.nom.value;
	var prenom=formulaire.prenom.value;
	var retapez=formulaire.retapez.value;

	var ok= verif_formulaire_enregistrement(login,password,nom,prenom,retapez);
	if(ok){
		Enregistre(login,password,nom,prenom)
	}
}
function verif_formulaire_enregistrement(login,password,retapez,nom,prenom,email){
	try { 
		if(login.length==0){
			throw "login obligatoire";
			return false;
		}
		if(login.length>20){
			throw "Taille maximum login = 20";
			return false;
		} 
		if(password.length==0){
			throw "password obligatoire"";
			return false;
		}
		if(retapez.length==0){
			throw "champ retapez obligatoire";
			return false;
		}
		if(nom.length==0){
			throw "nom obligatoire";
			return false;
		}
		if(prenom.length==0){
			throw "prenom obligatoire";
			return false;
		} 
		if(password.length>255){
			throw "Taille maximum password = 255;
			return false;
		} 
		if(retapez!=password){
			throw "les mots de passes de coresspondents pas";
			return false;
		} 
		if(password.length<4){
			throw "Taille minimum password = 4";
			return false;
		} 
	}
	catch(err) {
		message.innerHTML = "Error input: " + err;
	}
	return true;
}
function Enregistre(login,password,nom,prenom)
{
	console.log("Enregistrement de " + prenom);
	var url = "CreateUser";
	$.ajax({
		type:"GET",
		url:"CreateUser",
		data:"login="+login+"&name="+nom+"&prenom="+prenom+"&password="+password,
		datatype: "json",
		success: function(data){
			var utilisateur = JSON.parse(data);
			alert("Enregistrement de l'utilisateur "+utilisateur.login);
		},
		error: function (jqXHR, textStatus, errorThrown){alert(textStatus);}
	});
}



