function flipCard() {
    const card = document.querySelector('.card-account');
    card.style.transform = card.style.transform === 'rotateY(180deg)' ? '' : 'rotateY(180deg)';
}

function changeQuantity(choose) {
	var inputQuantity = document.getElementById("txt-quantity")
	var currentValue = parseInt(inputQuantity.value);

	if (choose == '+')
		inputQuantity.value = currentValue + 1;
	else {
		if (currentValue <= 1) return
		inputQuantity.value = currentValue - 1;
	}
}
