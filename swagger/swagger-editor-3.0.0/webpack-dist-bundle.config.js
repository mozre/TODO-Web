var path = require('path')
var fs = require('fs')


module.exports = require('./make-webpack-config.js')({
  _special: {
    minimize: true,
    sourcemaps: true,
    separateStylesheets: false,
    loaders: {
      "worker.js": ["worker-loader?inline=true&name=[name].js", "babel"]
    }
  },

  entry: {
    "swagger-editor-bundle": [
      './src/index.js'
    ]
  },

  output:  {
    path: path.join(__dirname, "dist"),
    publicPath: "/dist",
    library: "SwaggerEditorBundle",
    libraryTarget: "umd",
    filename: "[name].js",
    chunkFilename: "js/[name].js",
  },

})
