
function search() 
{
    var search = $('[name=searchQ]').val();
    if (search == "") {
        document.getElementById("main").innerHTML = "";
        return;
    }
    $.get('SearchYouTube',
    {'search' : search},
    function(data/*resulting data*/,status,xhr/*xmlobject*/)
    {
        document.getElementById("main").innerHTML = data;
    } );
}