Handlebars.templates = Handlebars.templates || {};
dl = {};
$('body').on('click', 'a[dl-async]', function (e) {
    e.preventDefault();
    var a = $(this);
    var state = {};
    state.action = a.attr('dl-async') || a.attr('href');
    state.action = state.action[0] == '/' ? state.action.substring(1) : state.action;
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
    $('#async-content').html(Handlebars.templates[state.action](state.context));
};



