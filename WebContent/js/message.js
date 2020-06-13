function Message(id,login,text,date,comments,like)
{
	this.id=id;
	this.login=login;
	this.text=text;
	this.date=date;
	if (comments == undefined)
		comments=[];
	this.comments=comments;
	if (like == undefined)
		like=0;
	this.like=like;
}
Message.prototype.getHTML=function()
{
	env.msg[this.id]=this;
	s="<div id=message_"+this.id+">";
	s+="<input type=\"button\" value=\"-\" onClick='javascript:replieMessage("+this.id+");'/> ";
	s+="<br/>";
	s+="<br/>";
	s+="<fieldset>";
	s+="Message ID: "+this.id+" ";
	s+="<br/>";
	s+=this.text;
	s+="<br/>";
	s+="Par "+this.login+" le "+this.date;
	s+="<br/>";
	s+="<br/>";
	s+="Like: "+this.like;
	s+="<br/>";
	s+="<br/>";
	s+="<fieldset>Commentaires";
	s+="<br/>";
	s+="<br/>";
	s+="<div id=\"espace_commentaire_"+this.id+"\">";
	//Ajout des commentaires existant
	if (this.comments.length!=0)
	{
		for (var j=0; j< this.comments.length; j++)
			s+=this.comments[j].getHTML();
	}
	s+="</div>";
	s+="<div id=\"commentaire\">";
	s+="<form class =\"commentaire\" action=\"javascript:(function(){return;})()\" onSubmit=\"javascript:new_comment("+this.id+")\">";
	s+="<input type=\"text\" id=\"commentaire_"+this.id+"\"/> ";
	s+="<input type=\"submit\" value=\"Commenter\"/>";
	s+="</form>";
	s+="</div>";
	s+="</fieldset>";
	s+="</div>";
	s+="<br/>";
	return s;
}

function Commentaire(id, auteur, texte, date, score){
	this.id=id;
	this.auteur=auteur;
	this.texte=texte;
	this.date=date;
	if (score == undefined)
		score=0;
	this.score=score;
}

Commentaire.prototype.getHTML=function()
{
	var s="Commentaire "+this.id+" ";
	s+="<br/>";
	s+="Texte: ";
	s+=this.texte;
	s+="<br/>";
	s+="J'aime: "
	s+=this.score;
	s+="<br/>";
	s+="Par "+this.auteur+" le "+this.date;
	s+="<br/>";
	s+="<br/>";
	return s;
}

//data:"key="+env.key+"&id_user="+env.id+"&from="+env.fromId+"&id_max"+env.mindId+"&id_min=-1 &nb=10",
			
function completeMessages()
{
	var url = "ListMessage";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&id="+env.id,
			datatype: "JSON",
			success: function(rep)
			{
				completeMessagesReponse(rep);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{
		var tab=getFromLocalDB(env.fromId,-1,env.minId,1);
		//alert(tab);
		completeMessagesReponse(JSON.stringify(tab));
	}
}

function completeMessagesMain()
{
	var url = "ListMessageMain";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&id="+env.id,
			datatype: "JSON",
			success: function(rep)
			{
				completeMessagesReponseMain(rep);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{
		var tab=getFromLocalDB(env.fromId,-1,env.minId,1);
		//alert(tab);
		completeMessagesReponseMain(JSON.stringify(tab));
	}
}

function completeMessagesReponse(rep) 
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
		$("#message_users").append(s);
		if (m.id > env.maxId)
			env.maxId = m.id;
		if (m.id < env.minId)
			env.minId = m.id;
		}
	}
}

function completeMessagesReponseMain(rep) 
{
	var lm = JSON.parse(rep);
	//alert(lm);
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
		if ((m.comments!=undefined) && (m.comments.length!=0))
		{
			for (var j=0; j< m.comments.length; j++)
			{
				com1 = new Commentaire(m.comments[j].id,m.comments[j].auteur,m.comments[j].texte, m.comments[j].date, m.comments[j].score)
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
		s+="<br/>";
		$("#liste_message").append(s);
		if (m.id > env.maxId)
			env.maxId = m.id;
		if (m.id < env.minId)
			env.minId = m.id;
		}
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function completeComment()
{
	var url = "ListComment";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&id="+env.id,
			datatype: "JSON",
			success: function(rep)
			{
				completeCommentReponse(rep);
			},
			error: function (jqXHR, textStatus, errorThrown)
			{
				//alert(textStatus);
			},
		});
	}
}

function completeCommentResponse(rep)
{
	alert("A voir avec le prof niveau servlet");
}


function new_comment(id)
{
	var text=$("#commentaire_"+id).val();
	if (!noConnection)
	{
		//var new_comment=new Commentaire(env.msg[id].comments.length+1,env.login,text,new Date());
		var url = "AddComment";
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&id_message="+id+"&comment="+text,
			datatype: "JSON",
			success: function(rep)
			{
				makeProfilPanel(env.id,env.login);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{
		//var new_comment=new Commentaire(env.msg[id].comments.length+1,{"id":env.id,"login":env.login},text,new Date());
		var new_comment=new Commentaire(env.msg[id].comments.length+1,env.login,text,new Date());
		newComment_response(id, JSON.stringify(new_comment));
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function newComment_response(id,rep)
{
	com=JSON.parse(rep,revival);
	//alert(com.getHTML());
	if((com!=undefined && com.erreur==undefined))
	{	
		var el=$("#espace_commentaire_"+id);
		el.append(com.getHTML());
		i=0
		while (i<localdb.length)
		{
			if(localdb[i].id==id)
			{
				localdb[i].comments.push(com);
				break;
			}
			i=i+1;
		}
		env.msg[id].comments.push(com);
		//alert(env.msg[id].comments[0].texte);
		/*
		if (noConnection)
			//localdb[id]=env.msg[id];
		else
			alert(com.erreur);*/
	}
}

function developpeMessage(id)
{
	var m=env.msg[id];
	//var el=$("#message_"+id+".comments");
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
	if (m.comments.length!=0)
	{
		for (var j=0; j< m.comments.length; j++)
		{
			com1 = new Commentaire(m.comments[j].id,m.comments[j].auteur,m.comments[j].texte, m.comments[j].date, m.comments[j].score)
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
	//s+="<input type=\"button\" value=\"+\" onClick='javascript:developpeMessage("+m.id+");' /> ";
	//$("#message_"+id).replaceWith("<img src=\"____\" onClick=\"javascript:developpeMessage("+id+")\"/>");
}



//MAIN
function new_message(id)
{
	var text=$("#main_message").val();
	var url = "AddMessageMain";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&message="+text,
			datatype: "JSON",
			success: function(rep)
			{
				makeMainPanel(env.id,env.login);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{

		var new_message=new Message(main_message[main_message.length-1].id+1, env.login,text,new Date());
		//function Message(id,login,text,date,comments)
		//alert(new_message.id);
		//alert(new_message.login);
		//alert(new_message.text);
		//alert(new_message.date);
		//alert(new_message.comments);
		newMessage_response(id,new_message);
		//newMessage_response(id,JSON.stringify(new_message));
	}
}

function newMessage_response(id,rep)
{
	//mess=JSON.parse(rep,revival);
	//alert(com.getHTML());
	mess=rep;
	//alert(mess.text)
	if((mess!=undefined && mess.erreur==undefined))
	{	
		var el=$("#liste_message");
		el.append(mess.getHTML());
		main_message[main_message.length]=mess;
		/*
		if (noConnection)
			//localdb[id]=env.msg[id];
		else
			alert(com.erreur);*/
	}
}

//new message profile users
function new_message_users(id)
{
	var text=$("#main_message").val();
	var url = "AddMessage";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&message="+text,
			datatype: "JSON",
			success: function(rep)
			{
				makeProfilPanel(env.id,env.login);
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{
		var new_message=new Message(localdb[localdb.length-1].id+1, env.login,text,new Date());
		newMessage_users_response(id,new_message);
	}
}

function newMessage_users_response(id,rep)
{
	//mess=JSON.parse(rep,revival);
	//alert(com.getHTML());
	mess=rep;
	//alert(mess.text)
	if((mess!=undefined && mess.erreur==undefined))
	{	
		var el=$("#message_users");
		el.append(mess.getHTML());
		localdb[localdb.length]=mess;
		//makeProfilPanel(env.id,env.login);
		/*
		if (noConnection)
			//localdb[id]=env.msg[id];
		else
			alert(com.erreur);*/
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function refreshMessage()
{
	if (env.query==undefined)
		return;
	if (!noConnection)
	{
		var url = "ListMessage"
		$.ajax({
			type:"GET",
			url:url,
			data:"key="+env.key+"&query="+env.query+"&from="+env.fromId+"&id_max=-1 &id_min="+ env.maxId+ "&nb=-1",
			datatype: "JSON",
			sucess : function(rep){ refreshMessageResponse(rep);},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);},
		});
	}
	else
	{
		var text=$("#text_new_message").val();
		var re_message=new Message(env.msg[id].comments.length+1,{"id":env.id,"login":env.login},text,new Date());
		refreshMessageResponse(JSON.stringify(re_message));
	}
}

function refreshMessageResponse(rep)
{
	var tab=JSON.parse(rep,revival)
	for (var i=tab.length-1;i>=0;i--)
	{
		var m=tab[i];
		$("#messages").prepend(m.getHTML());
		env.msg[m.id] = m;
		if (m.id > env.maxId)
			env.maxId = env.id;
		if ((env.minId<0)||(m.id<env.minId))
			env.minId = m.id;
	}
}
