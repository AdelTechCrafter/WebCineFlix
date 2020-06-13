/**
 * 
 */

//-----------------------------Constructeurs----------------------------------------------------------
function Message(id,auteur,texte,date,comments){
	this.id=id;
	this.auteur=auteur;
	this.texte=texte;
	this.date=date;
	if(comments==undefined){
		comments=[];
	}
	this.comments=comments;
}

function Commentaire(id,auteur,texte,date,like){
	this.id=id;
	this.auteur=auteur;
	this.texte=texte;
	this.date=date;
	if(like==undefined){
		this.likes=0;
	}else{
		this.likes=like;
	}
}
//---------------------------getHTML()----------------------------------------------------------------
Message.prototype.getHTML=function()
{
	env.msg[this.id]=this;
	ch="<div id=message_"+this.id+">";
	ch+="<input type=\"button\" value=\"-\" onClick='javascript:replieMessage("+this.id+");'/> ";
	ch+="<br/>";
	ch+="<br/>";
	ch+="<fieldset>";
	ch+="Message ID: "+this.id+" ";
	ch+="<br/>";
	ch+=this.text;
	ch+="<br/>";
	ch+="de "+this.login+" le "+this.date;
	ch+="<br/>";
	ch+="<br/>";
	ch+="Like: "+this.like;
	ch+="<br/>";
	ch+="<br/>";
	ch+="<fieldset>Commentaires";
	ch+="<br/>";
	ch+="<br/>";
	ch+="<div id=\"espace_commentaire_"+this.id+"\">";
	if (this.comments.length>0)
	{
		for (var i=0; i< this.comments.length; i++)
			ch+=this.comments[i].getHTML();
	}
	ch+="</div>";
	ch+="<div id=\"commentaire\">";
	ch+="<form class =\"commentaire\" action=\"javascript:(function(){return;})()\" onSubmit=\"javascript:new_comment("+this.id+")\">";
	ch+="<input type=\"text\" id=\"commentaire_"+this.id+"\"/> ";
	ch+="<input type=\"submit\" value=\"Commenter\"/>";
	ch+="</form>";
	ch+="</div>";
	ch+="</fieldset>";
	ch+="</div>";
	ch+="<br/>";
	return ch;
}

Commentaire.prototype.getHTML=function()
{
	var ch="Commentaire id: "+this.id+" ";
	ch+="<br/>";
	ch+="Texte: ";
	ch+=this.texte;
	ch+="<br/>";
	ch+="Likes: "
	ch+=this.likes;
	ch+="<br/>";
	ch+="de "+this.auteur+" le "+this.date;
	ch+="<br/>";
	ch+="<br/>";
	return ch;
}

//-------------------------------------CompleteMessages()----------------------------------------------

function completeMessagesReponse(rep) 
{
	var list_messages = JSON.parse(rep);
	for (var i=0; i < list_messages.length; i++)  
	{
		var m = list_messages[i];
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
		s+="<div id=\"commentaires"+m.id+"\">";
		if (noConnection)
		{
			if ((m.comments!=undefined) && (m.comments.length!=0))
			{
				for (var j=0; j< m.comments.length; j++)
				{
					com1 = new Commentaire(m.comments[j].id,m.comments[j].auteur,m.comments[j].texte, m.comments[j].date, m.comments[j].like)
					s+=com1.getHTML();
				}
			}
		}
		
}
//-------------------------------DeveloppeMessage()-------------------------------------
function developpeMessage(id)
{
	var m=env.msg[id];
	s="<div id=\"message_"+m.id+"\">";
	s+="<input type=\"button\" value=\"-\" onClick='javascript:replieMessage("+m.id+");'/> ";
	s+="<br/>";
	s+="<br/>";
	s+="<fieldset>";
	s+="Message ID: "+m.id+" ";
	s+="<br/>";
	s+=m.text;
	s+="<br/>"
	s+="de " +m.login + " le " + m.date;
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
	if (m.comments.length!=0)
	{
		for (var i=0; i< m.comments.length; i++)
		{
			com1 = new Commentaire(m.comments[i].id,m.comments[i].auteur,m.comments[i].texte, m.comments[i].date, m.comments[i].likes)
			s+=com1.getHTML();
		}
	}
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
	$("#message_"+id).replaceWith(s);
}

function replieMessage(id)
{
	var m = env.msg[id];
	var el=$("#message_"+id);
	el.html(" ");
	$("#message_"+id).append("<input type=\"button\" value=\"+\" onClick='javascript:developpeMessage("+id+");' />");
}

//-----------------new messages-----------------------------
function new_Comment(id)
{
	var text=$("#main_message").val();
	var url = "AddComment";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&text="+text,
			datatype: "JSON",
			success: function(rep)
			{
				makeMainPanel(env.id,env.login);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
}
