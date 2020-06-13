/**
 * 
 */
function revival(key,value){
	if(value.comments!=undefined){
		var c= new Message(value.id,value.auteur,value.text,value.date,value.comments);
		return c;
	}
	else if(value.text!=undefined){
		var c= new Commentaire(value.id,value.auteur,value.texte,value.date);
		return c;
	}
	else if(key=="date"){
		var d= new Date(date);
		return d;
	}
	else{
		return value;
	}
}





