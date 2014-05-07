PH = {};
PH.templates = {};
PH.Router = function () {
    this.routes = {};
};

PH.Router.prototype.resolve = function (link) {
    if (!link || link.length == 0) {
        link = "/";
    }

    return _.find(this.routes, function (value, key) {
        if (new RegExp(key).test(link)) {
            return true;
        }
    });
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
