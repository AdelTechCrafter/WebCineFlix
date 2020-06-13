function mdp_lost(formulaire)
{
	var mail=formulaire.mail.value;
	var ok=false;
	if (mail==0)
		func_erreur("Veuillez rentrer un mail");
	else
		func_pass("Un mail contenant votre mot de passe vous a été envoyé");
}