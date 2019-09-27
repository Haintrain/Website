/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";
class SaleItem {

    constructor(product, quantityPurchased) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantityPurchased;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }
}

class ShoppingCart {

    constructor() {
        this.saleItems = new Array();
    }

    reconstruct(sessionData) {
        for (let saleItem of sessionData.saleItems) {
            this.addItem(Object.assign(new SaleItem(), saleItem));
        }
    }

    getItems() {
        return this.saleItems;
    }

    addItem(saleItem) {
        this.saleItems.push(saleItem);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.saleItems) {
            total += item.getItemTotal();
        }
        return total;
    }

}

var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.config(function ($sessionStorageProvider, $httpProvider) {
    // get the auth token from the session storage
    let authToken = $sessionStorageProvider.get('authToken');

    // does the auth token actually exist?
    if (authToken) {
        // add the token to all HTTP requests
        $httpProvider.defaults.headers.common.Authorization = 'Basic ' + authToken;
    }
});

module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:categories');
});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.controller('ProductController', function (productDAO, categoryDAO) {
    this.products = productDAO.query();
    this.categories = categoryDAO.query();

    this.selectAll = function () {
        this.products = productDAO.query();
    };

    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"categories": selectedCat});
    };
});

module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window, $http) {
    this.signInMessage = "Please sign in to continue.";
    let ctrl = this;
    this.signIn = function (username, password) {
        
        let authToken = $window.btoa(username + ":" + password);
        $sessionStorage.authToken = authToken;
        $http.defaults.headers.common.Authorization = 'Basic ' + authToken;
        
        signInDAO.get({'username': username},
        
                function (customer) {
                    $sessionStorage.customer = customer;
                    $window.location.href = '.';
                },
                function () {
                    ctrl.signInMessage = 'Sign in failed. Please try again.';
                }
        );
    };
    
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer,
                function () {
                    delete $sessionStorage.customer;
                    $window.location = 'signin.html';
                },
                function (error) {
                    console.log(error);
                }
        );
    };
});
module.controller('CartController', function (saleDAO, cart, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;
    var item;
    this.howMany = function (product) {
        $sessionStorage.selectedProduct = product;
        $window.location = 'quantity.html';
    };
    this.addToCart = function (quantity) {
        if ($sessionStorage.selectedProduct.quantityInStock < quantity) {
            alert("Not enough stock");
        } else {
            item = new SaleItem($sessionStorage.selectedProduct, quantity);
            cart.addItem(item);
            $sessionStorage.cart = cart;
            $window.location = 'products.html';
        }

    };
    this.checkOut = function () {
        cart.setCustomer($sessionStorage.customer);
        saleDAO.save(cart);
        delete $sessionStorage.cart;
        $window.location = 'thanks.html';
    };
});

module.controller('PageController', function ($sessionStorage, $window) {
    this.home = function () {
        $window.location = 'index.html';
    };
    this.home2 = function () {
        $window.location = 'index2.html';
    };
    this.products = function () {
        $window.location = 'products.html';
    };
    this.cart = function () {
        $window.location = 'shoppingcart.html';
    };
    this.out = function () {
        delete $sessionStorage.authToken;
        delete $sessionStorage.customer;
        $window.location = 'index2.html';
    };
    this.create = function () {
        $window.location = 'account.html';
    };
    this.in = function () {
        $window.location = 'signin.html';
    };
});
