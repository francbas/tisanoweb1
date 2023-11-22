const path = require('path');
// const HtmlWebpackPlugin = require('html-webpack-plugin/index');


// oggetto configuraione comune
var basicConfig = {
    // Aggiungere configurazione comune a più bundles
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
        ],

    },
};

// oggetto configurazione dell'applicazione
var componentConfig = Object.assign({}, basicConfig, {
    name: "index",
    entry: {
        prova: '/src/app/__prova.js',
        prova2: '/src/app/__prova2.js',
        // prova: './assets/components/prova/prova',
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, '../main/webapp/resources/app/js'),
        clean: true,
    },
});

// Return Array delle configurazioni
module.exports = [
    componentConfig,
];
