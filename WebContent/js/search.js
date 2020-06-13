function rechercher(formulaire)
{
	i=0
	bool=false;
	while (i<localusers.length)
	{
		if (localusers[i].login==formulaire.search.value)
		{
			bool=true;
			break;
		}
		i=i+1
	}
	if (bool)
		makeProfilPanel(localusers[i].id,localusers[i].login);
	else
		alert("Non trouvé");
}