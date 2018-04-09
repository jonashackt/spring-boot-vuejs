var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API_PORT: JSON.stringify(process.env.SERVER_PORT)
})
