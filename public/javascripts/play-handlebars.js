PH = {};
PH.templates = {};
PH.Router = function () {
    this.routes = {};
};

PH.Router.prototype.resolve = function (link) {
    var result = null;
    if (!link || link.length == 0){
        link = "/";
    }

    _.each(this.routes, function (value, key) {
        if (!result && new RegExp(key).test(link)) {
            result = value;
        }
    });
    return result;
};

PH.router = new PH.Router();

PH.renderTemplate = function (link, context) {
    var templateNameGetter = PH.router.resolve(link);
    if (templateNameGetter) {
        var template = PH.templates[templateNameGetter(context)];
        if (template) {
            return template(context);
        }
    }
    return "";
};
