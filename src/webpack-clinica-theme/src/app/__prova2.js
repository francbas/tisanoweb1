import _ from 'lodash';

function component() {
    const element = document.createElement('div');

    // Lodash, now imported by this script
    element.innerHTML = _.join(['JAVASCRIPT - __prova2.js', ' HtmlWebpackPlugin PROVA 2 CARICATO - JS'], ' ');

    return element;
}

document.body.appendChild(component());