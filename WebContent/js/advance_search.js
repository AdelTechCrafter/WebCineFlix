function advance_search(formulaire)
{
	var content=formulaire.content.value;
	var date_1=formulaire.date_1.value;
	var date_2=formulaire.date_2.value;
	var ok=verif_formulaire_search(content,date_1,date_2)
	if (ok)
		advance_search_res(content,date_1,date_2);
}

function verif_formulaire_search(content,date_1,date_2)
{
	//ajouter des regex pour verif la date
	if (content.length==0)
	{
		alert("Contenu obligatoire");
		return false;
	}
	if (date_1.length==0)
	{
		alert("Date début obligatoire");
		return false;
	}
	if (date_2.length==0)
	{
		alert("Date fin obligatoire");
		return false;
	}
	return true;
}

function advance_search_res(content,date_1,date_2)
{
	console.log("Recherche du message contenant " + content);
	console.log("Date début " + date_1);
	console.log("Date fin " + date_2);
	var url = "SearchMessage";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+content+"&date_1="+date_1+"&date_2="+date_2,
			datatype: "JSON",
			success: function(rep)
			{
				advance_search_res2(content,date_1,date_2)
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
}


function advance_search_res2(rep) 
{
	var lm = JSON.parse(rep);
	for (var i=0; i < lm.length; i++)  
	{
		var m = lm[i];
		if (m != null)
		{
			env.msg[m.id]=m;
		//s="<br/>";
		s="<div id=\"message_"+m.id+"\">";
		s+="<input type=\"button\" value=\"-\" onClick='javascript:replieMessage("+m.id+");'/> ";
		s+="<br/>";
		s+="<br/>";
		s+="<fieldset>";
		s+="Message ID: "+m.id+" ";
		s+="<br/>";
		s+=m.text;
		s+="<br/>"
		s+="Par " +m.login + " le " + m.date;
		s+="<br/>";
		s+="<br/>";
		s+="Like: "+m.like;
		s+="<br/>";
		s+="<br/>";
		s+="<fieldset>Commentaires";
		s+="<br/>";
		s+="<br/>";
		s+="<div id=\"espace_commentaire_"+m.id+"\">";
		//Ajout des commentaires existant
		if (noConnection)
		{
			if ((m.comments!=undefined) && (m.comments.length!=0))
			{
				for (var j=0; j< m.comments.length; j++)
				{
					com1 = new Commentaire(m.comments[j].id,m.comments[j].auteur,m.comments[j].texte, m.comments[j].date, m.comments[j].score)
					s+=com1.getHTML();
				}
			}
		}
		else
			completeComment();
		s+="</div>";
		s+="<div id=\"commentaire\">";
		s+="<form class =\"commentaire\" action=\"javascript:(function(){return;})()\" onSubmit=\"javascript:new_comment("+m.id+")\">";
		s+="<input type=\"text\" id=\"commentaire_"+m.id+"\"/> ";
		s+="<input type=\"submit\" value=\"Commenter\"/>";
		s+="</form>";
		s+="</div>";
		s+="</fieldset>";
		s+="</div>";
		s+="</fieldset>";
		s+="<br/>";
		$("#message_search").append(s);
		if (m.id > env.maxId)
			env.maxId = m.id;
		if (m.id < env.minId)
			env.minId = m.id;
		}
	}
}
