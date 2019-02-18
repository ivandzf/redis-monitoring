"use strict";
const merge = require("webpack-merge");
const prodEnv = require("./prod.env");

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  SSE_URL: '"http://localhost:8080/redis/info"',
  API_URL: '"http://localhost:8080/api"'
});
