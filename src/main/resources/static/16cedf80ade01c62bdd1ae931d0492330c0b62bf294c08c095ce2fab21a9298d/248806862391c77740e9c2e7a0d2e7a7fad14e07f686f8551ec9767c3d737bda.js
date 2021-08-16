$(".dropdown-btn").click(function () {
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display.localeCompare("") == 0) {
        dropdownContent.style.display = "block";
    } else {
        dropdownContent.style.display = "";
    }
});

$("li.dropdown-dropdown a").on("hover", function (event) {
    $(this).parent().toggleClass("open");
});

$("body").on("hover", function (e) {
    if (
        !$("li.dropdown-dropdown").is(e.target) &&
        $("li.dropdown-dropdown").has(e.target).length === 0 &&
        $(".open").has(e.target).length === 0
    ) {
        $("li.dropdown-dropdown").removeClass("open");
    }
});

$(document).ready(function () {
    $("li.dropdown ul.dropdown-menu li").click(function (event) {
        event.toElement.parentElement.click();
    });
});

function openNav() {
    $("#sidenav").css("width", "250px");
    document.getElementById("top-header-p").innerHTML =
        '<i class="fa fa-phone"></i> 0966 28 38 99';
    $("#top-header-p")
        .css({
            width: $(window).width() - 250,
            transition: "width .3s ease",
            position: "relative",
        })
        .animate({ left: $(this).width() - a.width() });
}

function closeNav() {
    document.getElementById("sidenav").style.width = "0";
    document.getElementById("top-header-p").innerHTML =
        'Để hỗ trợ nhanh nhất: &nbsp;<i class="fa fa-phone"></i> 0966 28 38 99';
    $("#top-header-p").css({ width: "100%", transition: "width .3s ease" });
}

function readmoreBtn() {
    var dots = document.getElementById("dots");
    var moreText = document.getElementById("more");
    var xemthemBtn = document.getElementById("xemthem");
    var rutgonBtn = document.getElementById("rutgon");

    if (dots.style.display === "none") {
        dots.style.display = "inline";
        xemthemBtn.style.display = "inline";
        rutgonBtn.style.display = "none";
        moreText.style.display = "none";
        $(".blog-content").css("height", "30rem");
    } else {
        dots.style.display = "none";
        xemthemBtn.style.display = "none";
        rutgonBtn.style.display = "inline";
        moreText.style.display = "inline";
        $(".blog-content").css("height", "100%");
    }
}

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides((slideIndex += n));
}

function currentSlide(n) {
    showSlides((slideIndex = n));
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    if (n > slides.length) {
        slideIndex = 1;
    }
    if (n < 1) {
        slideIndex = slides.length;
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
}
