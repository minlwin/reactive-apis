db.createUser({
	user: 'locationdb',
	pwd: 'locationdb',
	roles: [
		{
			role: 'readWrite',
			db: 'locationdb'
		}
	]
})