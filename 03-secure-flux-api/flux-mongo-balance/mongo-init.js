db.createUser({
	user: 'balanceusr',
	pwd: 'balancepwd',
	roles: [
		{
			role: 'readWrite',
			db: 'balancedb'
		}
	]
})