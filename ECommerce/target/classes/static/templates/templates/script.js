$(document).ready(function() {

      if ($("#product-list").length) {
          displayProducts();
      }


      $(document).on('click', '.add-to-cart', function() {
          const productId = $(this).data('id');
          addProductToBasket(productId);
      });


      if ($("#basket-items").length) {
          fetchBasketItems();
      }


      $(document).on('click', '.remove-from-cart', function() {
          const itemId = $(this).data('id');
          removeItemFromBasket(itemId);
      });
});

function displayProducts() {
    $.ajax({
        url: 'http://localhost:8080/product/all',
        method: 'GET',
        success: function(products) {
            renderProducts(products);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching products:', xhr.responseText);
        }
    });
}


function renderProducts(products) {
    const productList = $('#product-list');
    productList.empty();

    products.forEach(product => {
        const productItem = `
            <div class="product-item">
                <h3>${product.name}</h3>
                <p>${product.description}</p>
                <p>Price: $${product.price.toFixed(2)}</p>
                <p>Stock: ${product.stock}</p>
                <button class="add-to-cart btn" data-id="${product.id}">Add to Cart</button>
            </div>
        `;
        productList.append(productItem);
    });
}

function addProductToBasket(productId) {
    // Fetch current basket items to check if the product is already in the basket
    $.ajax({
        url: 'http://localhost:8080/api/baskets/all',
        method: 'GET',
        success: function(basketItems) {
            const existingItem = basketItems.find(item => item.product.id === productId);
            if (existingItem) {
                updateBasketQuantity(existingItem.id, existingItem.quantity + 1);
            } else {
                const basketItem = {
                    customer: { id: 1 },
                    product: { id: productId },
                    quantity: 1
                };
                $.ajax({
                    url: 'http://localhost:8080/api/baskets/add',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(basketItem),
                    success: function() {
                        alert('Product added to basket!');
                        fetchBasketItems();
                    },
                    error: function() {
                        alert('Failed to add product to basket. Please try again.');
                    }
                });
            }
        },
        error: function(xhr) {
            console.error('Error fetching basket items:', xhr.responseText);
        }
    });
}


function fetchBasketItems() {
    $.ajax({
        url: 'http://localhost:8080/api/baskets/all',
        method: 'GET',
        success: function(basketItems) {
            renderBasketItems(basketItems);
        },
        error: function(xhr) {
            console.error('Error fetching basket items:', xhr.responseText);
        }
    });
}


function updateBasketQuantity(basketId, newQuantity) {
    $.ajax({
        url: `http://localhost:8080/api/baskets/${basketId}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ quantity: newQuantity }),
        success: function(response) {
            fetchBasketItems();
        },
        error: function(xhr) {
            console.error('Error updating basket quantity:', xhr.responseText);
        }
    });
}
function decreaseProductStock(productId) {
    $.ajax({
        url: `http://localhost:8080/product/${productId}/decreaseStock`,
        method: 'PUT',
        success: function(response) {
            displayProducts();
        },
        error: function(xhr) {
            console.error('Error decreasing product stock:', xhr.responseText);
        }
    });
}


function renderBasketItems(basketItems) {
    const basketItemsContainer = $('#basket-items');
    basketItemsContainer.empty();

    basketItems.forEach(item => {
        const basketItemCard = `
            <div class="product-item">
                <h3>${item.product.name}</h3>
                <p>Quantity: ${item.quantity}</p>
                <p>Total Price: $${(item.product.price * item.quantity).toFixed(2)}</p>
                <button class="remove-from-cart btn" data-id="${item.id}">Remove from Cart</button>
                <button class="buy-btn btn" data-id="${item.product.id}" data-basket-id="${item.id}" data-quantity="${item.quantity}">Buy</button>

            </div>
        `;
        basketItemsContainer.append(basketItemCard);
    });
}


$(document).on('click', '.buy-btn', function() {
    const productId = $(this).data('id');
    const basketId = $(this).data('basket-id');
    let currentQuantity = $(this).data('quantity');

    updateBasketQuantity(basketId, currentQuantity + 1);


    decreaseProductStock(productId);
});


function removeItemFromBasket(itemId) {
    $.ajax({
        url: `http://localhost:8080/api/baskets/${itemId}`,
        method: 'DELETE',
        success: function() {
            alert('Item removed from basket');
            fetchBasketItems();
        },
        error: function(xhr) {
            console.error('Error removing item from basket:', xhr.responseText);
        }
    });
}



$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        const loginData = {
            email: $('#email').val(),
            password: $('#password').val(),
        };

        $.ajax({
            url: 'http://localhost:8080/api/customers/login',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function(response) {
                alert('Login successful');
                window.location.href = 'index.html';
            },
            error: function(error) {
                console.error('Login failed:', error);
                alert('Login failed');
            }
        });
    });
});


        $(document).ready(function() {
            $('#registerForm').on('submit', function(e) {
                e.preventDefault();

                const userData = {
                    firstName: $('#firstName').val(),
                    lastName: $('#lastName').val(),
                    email: $('#email').val(),
                    password: $('#password').val(),
                };

                $.ajax({
                    url: 'http://localhost:8080/api/customers/add',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(userData),
                    success: function(response) {
                        alert('Registration successful');
                        window.location.href = 'login.html';
                    },
                    error: function(error) {
                        console.error('Registration failed:', error);
                        alert('Registration failed');
                    }
                });
            });
        });

        $(document).on('click', '.remove-from-cart', function() {
            const itemId = $(this).data('id');
            removeItemFromBasket(itemId);
        });