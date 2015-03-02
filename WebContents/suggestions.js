/**
 * Created by kevin on 3/1/15.
 */

function Suggestions() {

}


Suggestions.prototype.requestSuggestions = function (autoSuggestControl /*:AutoSuggestControl*/,
                                                          typeAhead /*:boolean*/) {
    var textboxValue = autoSuggestControl.textbox.value;
    var suggestionsValues = [];

    if (textboxValue.length > 0){
        var xmlhttp;
        xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                //search for matching states
                var suggestions = xmlhttp.responseXML.getElementsByTagName("suggestion");
                for (var i = 0; i < suggestions.length; i++) {
                    var suggestionValue = suggestions[i].getAttribute("data");
                    console.log(suggestionValue);
                    suggestionsValues.push(suggestionValue);
                }
                //provide suggestions to the control
                autoSuggestControl.autosuggest(suggestionsValues, typeAhead);
            }
        };
        xmlhttp.open("GET","suggest?q="+textboxValue,true);
        xmlhttp.send();
    }
};