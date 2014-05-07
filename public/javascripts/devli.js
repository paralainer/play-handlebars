dl = {};
$('body').on('click', 'a[dl-async]', function (e) {
    e.preventDefault();
    var a = $(this);
    var state = {};
    state.link = a.attr('dl-async') || a.attr('href');
    var link = a.attr('href');
    $.get(link + "?json=true", function (context) {
        state.context = context;
        dl.showContent(state);
        history.pushState(state, "", link);
    });
});

window.addEventListener('popstate', function (e) {
    dl.showContent(e.state);
});

dl.showContent = function(state){
    $('#async-content').html(PH.renderTemplate(state.link, state.context));
};



