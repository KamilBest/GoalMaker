var updateSliderValue;
var hs = document.getElementById("hiddenslider");

updateSliderValue = function (slider, handle) {
    var children, i, results, val, values;
    if (handle === null) {
        handle = 0;
    }
    children = slider.getElementsByClassName('noUi-handle');
    values = slider.noUiSlider.get();
    i = 0;
    results = [];
    while (i < children.length) {
        if (children.length === 1) {
            val = parseInt(values);
        } else {
            val = parseInt(values[i]);
        }
        children[i].dataset.value = val;
        hs.value = val;
        results.push(i++);
    }
    return results;
};

var singleSlider;

singleSlider = document.getElementById('slider');

noUiSlider.create(singleSlider, {
    animationDuration: 300,
    start: [0],
    step: 1,
    range: {
        'min': 1,
        'max': 100
    },
    pips: {
        mode: 'count',
        values: 10,
        stepped: true
    }
});

function clickOnPip() {
    var value = Number(this.getAttribute('data-value'));
    singleSlider.noUiSlider.set(value);
}

function getPips() {
    var pips = singleSlider.querySelectorAll(".noUi-value");
    for (var i = 0; i < pips.length; i++) {
        pips[i].style.cursor = 'pointer';
        pips[i].addEventListener('click', clickOnPip);
    }
}

singleSlider.noUiSlider.onload = getPips();

singleSlider.noUiSlider.on('update', function () {
    return getPips();
});

singleSlider.noUiSlider.on('update', function () {
    return updateSliderValue(singleSlider);
});

var button1 = document.getElementById('update-1'),
    button2 = document.getElementById('update-2'),
    button3 = document.getElementById('update-3'),
    button4 = document.getElementById('reset');

function updateSliderRange(min, max) {
    singleSlider.noUiSlider.updateOptions({
        range: {
            'min': min,
            'max': max
        },
        start: [min]
    });
    hs.setAttribute("max", max);
    hs.setAttribute("min", min);
}

button1.addEventListener('click', function () {
    updateSliderRange(100, 1000);
});

button2.addEventListener('click', function () {
    updateSliderRange(1000, 10000);
});

button3.addEventListener('click', function () {
    updateSliderRange(10000, 100000);
});

button4.addEventListener('click', function () {
    updateSliderRange(1, 100);
});

function mapRange(value, low1, high1, low2, high2) {
    return low2 + (high2 - low2) * (value - low1) / (high1 - low1);
}
