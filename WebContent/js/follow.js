function follow ()
{
	var el=$("#follow");
	el.html(" ");
	$("#follow").append("<input type=\"button\" value=\"Ne plus suivre\" onclick='Javascript:stopfollow()' /><br/><br/>");
	if (!noConnection){}
	else
		reponseFollow({});
}

function reponseFollow(rep)
{
	if(rep.erreur==undefined)
	{
		env.follows.add(env.fromId)
		if (noConnection)
			follows[env.id].add(env.fromId);
	}
	else
		alert(rep.erreur);
}

function stopfollow ()
{
	var el=$("#follow");
	el.html(" ");
	$("#follow").append("<input type=\"button\" value=\"suivre\" onclick='Javascript:follow()' /><br/><br/>");
	if (!noConnection){}
	else
		reponseStopFollow({});
}

function reponseStopFollow(rep)
{
	if(rep.erreur==undefined)
	{
		env.follows.delete(env.fromId)
		if (noConnection)
			follows[env.id].delete(env.fromId);
	}
	else
		alert(rep.erreur);
}