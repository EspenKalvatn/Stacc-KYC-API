function get_action_src() {
    var base_src = "http://localhost:8080/check/"
    var form_value = document.getElementById("search-input").value;
    var search_type;

    if (form_value.match(/^[0-9]+$/) != null) {
        search_type = "organization/"
    } else {
        search_type = "person/"
    }
    var pep_check = document.getElementById("pep_check")
    pep_check.action = base_src + search_type + form_value
}