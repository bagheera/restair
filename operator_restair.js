/*extern ufJS, ufJSActions, ufJSParser, openUILink */ 

var lookup_restair = {
  description: "Lookup flights on restair",
  shortDescription: "RestAir",
  icon: "http://blogmarks.net/favicon.ico",
  scope: {
    semantic: {
      "hCalendar" : "dtstart"
    }
  },
  doAction: function(semanticObject, semanticObjectType) {
    if (semanticObjectType == "hCalendar") {
		    var hcalendar = semanticObject;
        var dtStartDate = Microformats.dateFromISO8601(hcalendar.dtstart);
        var journeyDate = dtStartDate.getDate() + "-" + (dtStartDate.getMonth()+1) + "-" + dtStartDate.getFullYear();		
      var result = "http://localhost:3000/v1/flights?journeyDate=" + journeyDate + "&from=BANGALORE" + "&to=" +semanticObject.location;
			return result;
    }
  }
};

SemanticActions.add("lookup_restair", lookup_restair);
