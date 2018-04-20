var webpack = require('webpack');
var path = require('path');
var OpenBrowserPlugin = require('open-browser-webpack-plugin');

module.exports = {
  devtool: 'eval-source-map',
  devServer: {
    /*headers: {
       'Access-Control-Allow-Origin': '*',
       'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
     },*/
    historyApiFallback: true,
    hot: true,
    inline: true,
    contentBase: './webapp/',
    progress: true,
    port: 8000,
    proxy: {
      //    '\*': {
      //    target:'http://10.10.1.7:8088',
      //    secure: false
      //  },
      'dev.html': {
        target: './'
      },
      '\dev\index.css': {
        target: './'
      },
      '\dev\images\*': {
        target: './'
      },
      '/modules/*': {
        target: 'http://10.10.1.7:8088',
        secure: false
      }, 
    }
  },
  entry: [
    'webpack-dev-server/client?http://localhost:8000',
    'webpack/hot/dev-server',
    path.resolve(__dirname, 'react/main.js')
  ],
  output: {
    path: __dirname + '/webapp/dev',
    publicPath: '/',
    filename: './bundle.js',
    chunkFilename: "[id].bundle.js"
  },
  module: {
    loaders: [{
      test: /\.css$/,
      loader: 'style-loader!css-loader'
    }, {
        test: /\.js[x]?$/,
        include: path.resolve(__dirname, 'react'),
        exclude: /node_modules/,
        loaders: ['react-hot', 'babel']
      }, {
        test: /\.(png|jpg)$/,
        loader: 'url-loader'
      }, {
        test: /\.(woff|woff2|eot|ttf|svg)(\?.*$|$)/,
        loader: 'url'
      }]
  },
  resolve: {
    extensions: ['', '.js', '.jsx'],
  },
  plugins: [
    new OpenBrowserPlugin({
      url: 'http://localhost:8000/dev.html'
    })
  ]
};
