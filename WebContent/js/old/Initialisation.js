/**
 * 
 */
function init()
{
	noConnection=true;
	env=new Object();
	SetVirtualDB();
}
function SetVirtualDB()
{
	localdb=[];
	follows=[];
	var a1={"id":1,"login":"magic"};
	var a2={"id:":2,"login":"magic2"};
	var a3={"id":3, "login":"magic3"};
	follow[1]=new set()
	follows[1].add(2)
	follows[1].add(3)
	follows[2]=new Set();
	follows[2].add(3);
	
	var c=new Commentaire(3,"magic3","what's up?",new Date())
	localdb[0]=new Message(2,"magic","hello from magic",new Date(),c);
	localdb[1]=new Message(2,"magic2","hello from magic2",new Date());
}

function getFromLocalDb(fromid,idMin,idMax,nbMax)
{
	var messages=[]
	//affichage des nbmax messages
	for(var i=0;i<localdb.length;i++){
		messages[i]=localdb[i]
	}
	return messages
}