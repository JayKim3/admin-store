$(document).ready(function() {
    $('#pagination').pagination({
        dataSource:     [
                        {name: "hello1"},
                        {name: "hello2"},
                        {name: "hello3"},
                        {name: "hello4"},
                        {name: "hello5"},
                        {name: "hello6"},
                        {name: "hello7"},
                        {name: "hello8"},
                        {name: "hello9"},
                        {name: "hello10"},
                        {name: "hello11"},
                        {name: "hello12"},
                        {name: "hello13"},
                        {name: "hello14"},
                        {name: "hello15"},
                        {name: "hello16"},
                        {name: "hello17"},
        ],
        callback: function(data, pagination) {
        // template method of yourself
        // var html = template(data);
        // dataContainer.html(html);
    }
    })
})