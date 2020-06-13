function init()
{
	noConnection=false;
	env=new Object();
	SetVirtualDB();
}

function SetVirtualDB()
{
	localdb=[];
	localusers=[];
	follows=[];
	main_message=[];

	var c1=new Commentaire(1,"user3","hum",new Date(),0);
	var c2=new Commentaire(2,"user4","humhum",new Date(),0);

	//Liste message
	main_message[0]=new Message(47,"3408748","test2",new Date());
	main_message[1]=new Message(48,"3408748","test2",new Date());
	main_message[2]=new Message(49,"3408748","test3",new Date(),[c1]);

	//LOGIN/PWD
	localusers[0]=new Users(1,"Marie","secret1234");
	localusers[1]=new Users(2,"John","secret1234");

	
	follows[1]=new Set()
	follows[1].add(2);
	follows[2]=new Set();
	follows[2].add(1);

	//LocalDB
	localdb[0]=new Message(41,"3408747","Bla",new Date());
	localdb[1]=new Message(42,"3408748","Blabla",new Date());
	localdb[2]=new Message(43,"3408749","Hello",new Date());
	localdb[3]=new Message(44,"3408750","Buenos Dias", new Date(),[c1,c2]);
}

function getFromLocalDB(fromId,minId,maxId,nbMax)
{
	var tab=[];
	var nb=0;
	var f=new Set();
	if (fromId>0)
		f=follows[fromId];
	for (var i=localdb.length-1;i>=0;i--)
		tab[i]=localdb[i];
	return tab;
}

function Users(id,login, password){
	this.id=id;
	this.login=login;
	this.password=password;
}
