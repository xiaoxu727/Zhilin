 <!--GLOBAL VARIABLES-->
 var mapURL = 'http://shaofei.cartodb.com/api/v2/viz/1d8be4d0-7af0-11e4-9666-0e4fddd5de28/viz.json';
 var testURL = 'http://shaofei.cartodb.com/api/v2/viz/58848f86-7d7c-11e4-b795-0e4fddd5de28/viz.json';
 // var highSchoolURL = 'http://shaofei.cartodb.com/api/v2/viz/84656966-8c1f-11e4-a218-0e018d66dc29/viz.json';
 var highSchoolURL = 'http://shaofei.cartodb.com/api/v2/viz/84656966-8c1f-11e4-a218-0e018d66dc29/viz.json';
 var layersURL = 'http://shaofei.cartodb.com/api/v2/viz/84656966-8c1f-11e4-a218-0e018d66dc29/viz.json';
 var layers = null;
 var myLayer = null;
 var center_lat = '37.33';
 var center_lon = '-121.56';
 var initZoomLevel = 10;
 var map = null;
 var baseLayer = null; // 地图图层
 var testLayer = null; //点图层
 var scoreLayer = null; // 得分图层
 var highSchoolLayer = null; //高中图层
 var overlays = null;
 var cities = null;
 var myAccount = 'shaofei';
 var myAPIkey = '9aca14f956f59e76536bbab8b72f7126a8bb25b1';
 var currentClientXY = null;
 var currentLatLng = null;


 <!--DOCUMENT HANDLES-->
 $(document).ready(function() {
     main();
 });

 function main() {

     cities = new L.LayerGroup();

//     L.marker([39.61, -105.02]).bindPopup('This is Littleton, CO.').addTo(cities),
//     L.marker([39.74, -104.99]).bindPopup('This is Denver, CO.').addTo(cities),
//     L.marker([39.73, -104.8]).bindPopup('This is Aurora, CO.').addTo(cities),
//     L.marker([39.77, -105.23]).bindPopup('This is Golden, CO.').addTo(cities);

     overlays = {
         "Cities": cities
     };

     cartodb.createVis('map', highSchoolURL, {
         shareable: true,
         title: true,
         description: true,
         search: true,
         tiles_loader: true,
         center_lat: center_lat,
         center_lon: center_lon,
         zoom: initZoomLevel
     })
         .done(function(vis, layers) {

             baseLayer = layers[0];
             myLayer = layers[1];
             baseLayer.on('featureClick', function(e, latlng, pos, data, subLayerIndex) {
                 console.log(' baseLayer mouse click here ' + latlng);
             });
             //scoreLayer = layers[1];
             highSchoolLayer = layers[1];

             // testLayer = layers[1].getSubLayer(0);
             // scoreLayer = layers[1].getSubLayer(1);
             // highSchoolLayer = layers[1].getSubLayer(2);


             layers[1].on('featureClick', function(e, pos, latlng, data) {
                 console.log(' scoreLayer mouse click here ' + latlng);
             });
             myLayers = {
                 'score': scoreLayer
             }

             //var sublayer = layers[1].getSubLayer(0);

             // you can get the native map to work with it
             map = vis.getNativeMap();
             map.on('click', function(latlng, layerPoint, containerPoint, originalEvent) {
                 console.log('map click');
             });

             //添加右键菜单
             map.on('contextmenu', function(e) {
                 console.log('contextmenu y:' + e.containerPoint.y + ' ; x:' + e.containerPoint.x);
                 $('#contextMenu').hide();
                 var contextMeunPosition = GetContextMeunPosition(e.containerPoint.x + 2, e.containerPoint.y + 2);
                 $('#contextMenu').css("top", currentClientXY.y);
                 $('#contextMenu').css("left", currentClientXY.x);
                 currentLatLng = {
                     lng: e.latlng.lng,
                     lat: e.latlng.lat
                 };
                 $('#contextMenu').show();
             });

             // 隐藏右键菜单
             map.on('click', function(e) {
                 $('#contextMenu').hide();
             });

             // cartodb.createLayer(map, mapURL)
             //     .addTo(map)
             //     .on('done', function(layer) {
             //         var sublayer = layer.getSubLayer(0);
             //         //CreateSelector(sublayer);
             //         scoreLayer = layer;
             //         layer.on('featureClick', function(e, pos, latlng, data) {
             //             console.log('pointerLayer mouse click here ' + latlng);
             //         });

             //         L.control.layers(myLayers, overlays).addTo(map);
             //     })
             //     .on('error', function(err) {
             //         alert('some error occurred:' + err);
             //     });

             //CreateNewLayer(map, testURL, 'testLayer');
             //CreateNewLayer(map, highSchoolURL, 'highSchoolLayer');
             cartodb.createLayer(map, mapURL)
             //.addTo(map)
             .on('done', function(layer) {
                 var sublayer = layer.getSubLayer(0);
                 //CreateSelector(sublayer);
                 scoreLayer = layer;
                 layer.on('featureClick', function(e, pos, latlng, data) {
                     console.log('pointerLayer mouse click here ' + latlng);
                 });
                 // var myLayers = {
                 //         'socre': testLayer
                 //     }
                 //L.control.layers(myLayers, overlays,).addTo(map);
                 myLayers.testLayer = testLayer;


                 cartodb.createLayer(map, testURL)
                 //.addTo(map)
                 .on('done', function(layer) {
                     var sublayer = layer.getSubLayer(0);
                     //CreateSelector(sublayer);
                     testLayer = layer;
                     layer.on('featureClick', function(e, pos, latlng, data) {
                         console.log('pointerLayer mouse click here ' + latlng);
                     });
                     // var myLayers = {
                     //     'highSchoolLayer': highSchoolLayer,
                     //     'socre': scoreLayer

                     // }
                     L.control.layers(scoreLayer, highSchoolLayer, overlays).addTo(map);
                     //myLayers.highSchoolLayer = highSchoolLayer;
                 })
                     .on('error', function(err) {
                         alert('some error occurred:' + err);
                     });

             })
                 .on('error', function(err) {
                     alert('some error occurred:' + err);
                 });



             CreateSelector();

         }).error(function(err) {
             console.log(err);
         });
 }


 function CreateNewLayer(map, url, layerName) {


     cartodb.createLayer(map, url)
     //s .addTo(map)
     .on('done', function(layer) {
         //var sublayer = layer.getSubLayer(0);
         //CreateSelector(sublayer);

         // layer.on('featureClick', function(e, pos, latlng, data) {
         //     console.log('pointerLayer mouse click here ' + latlng);
         // });
         if (layerName == 'testLayer') {
             testLayer = layer;
         } else if (layerName == 'highSchoolLayer') {
             highSchoolLayer = layer;
         }
         L.control.layers(layer).addTo(map);
     })
         .on('error', function(err) {
             alert('some error occurred:' + err);
         });
 }

 //get map container bounds
 function GetMapContainerBounds() {
     var container = this.map.getContainer();
     return container;
 }

 //get context menu div poistion
 function GetContextMeunPosition(x, y) {
     var container = GetMapContainerBounds();
     var divWidth = $('#contextMenu').width();
     var divHeight = $('#contextMenu').height();
     var positionX = x;
     var positionY = y;
     if (x + divWidth > container.clientWidth) {
         positionX = container.clientWidth - divWidth;
     }
     if (y + divHeight > container.clientHeight) {
         positionY = container.clientHeight - divHeight;
     }
     currentClientXY = {
         x: positionX,
         y: positionY
     };
 }

 //get map div client center x and y
 function GetMapClientCenter() {
     var container = this.map.getContainer();
     var x = Math.floor(container.clientWidth / 2) + container.clientLeft;
     var y = Math.floor(container.clientHeight / 2) + container.clientTop;
     centerXY = {
         x: x,
         y: y
     };
     return centerXY;
 }

 // 绑定图层开关
 function CreateSelector() {

     var $options = $('#layer_selector input');
     $options.change(function(e) {
         //get the count of the selected layer
         var $li = $(e.target);
         //var  = $li.attr('value');

         var layerName = $li.attr('value');


         if ($li.is(':checked')) {
             if (layerName == "test") {
                 map.addLayer(testLayer);
                 //showSubLayer('data', testLayer);
                 //myLayer.removeLayer(testLayer);
             } else if (layerName == "score") {
                 map.addLayer(scoreLayer);
                 //showSubLayer('score', scoreLayer);
                 //myLayer.removeLayer(scoreLayer);
             } else if (layerName == 'house') {
                 map.addLayer(cities);
             } else if (layerName == 'highSchool') {
                 map.addLayer(highSchoolLayer);
                 //showSubLayer('highschoolranking_geocode', highSchoolLayer);
                 //myLayer.removeLayer(highSchoolLayer);
             }
         } else {
             if (layerName == "test") {
                 map.removeLayer(testLayer);
                 // closeSubLayer('data', testLayer);
             } else if (layerName == "score") {
                 // closeSubLayer('score', scoreLayer);
                 map.removeLayer(scoreLayer);
             } else if (layerName == 'house') {
                 map.removeLayer(cities);
             } else if (layerName == 'highSchool') {
                 // closeSubLayer('highschoolranking_geocode', highSchoolLayer);
                 map.removeLayer(highSchoolLayer);
             }
         }

     });
 }

 function showSubLayer(layerName, layer) {
     var sql = new cartodb.SQL({
         user: 'shaofei'
     });
     var query = "select * from " + layerName;
     layer.setSQL(query);
     // body...
 }

 function closeSubLayer(layerName, layer) {
     var sql = new cartodb.SQL({
         user: 'shaofei'
     });
     var query = "select * from " + layerName + ' where 1=2';
     layer.setSQL(query);
     // body...
 }
 //show add new house  record div
 function showAddHouseRecordDIV() {
     $('#contextMenu').hide();
     var divWidth = $('#houseNewRecordDiv').width();
     var divHeight = $('#houseNewRecordDiv').height();
     var mapCenterXY = GetMapClientCenter();
     var centerX = mapCenterXY.x - Math.floor(divWidth / 2);
     var centerY = mapCenterXY.y - Math.floor(divHeight / 2);
     $('#houseNewRecordDiv').css("top", centerY);
     $('#houseNewRecordDiv').css("left", centerX);
     // $("#map").unbind();
     showdiv();
     $('#houseNewRecordDiv').show();
 }
 
 // add new record
 function InsertRecord() {
     var record = GetHouseNewRecordDiv();
     var url = "http://" + myAccount + ".cartodb.com/api/v2/sql?q=INSERT INTO house(agent,address,lat,lon,name, the_geom) VALUES ('" + record.agent + "','" + record.address + "'," + currentLatLng.lat + "," +
         currentLatLng.lng + ",'" + record.houseName + "',  ST_SetSRID(ST_Point(" + currentLatLng.lat + "," + currentLatLng.lng + "),4326))&api_key=" + myAPIkey;
     data = {
         q: "INSERT INTO house(agent,address,lat,lon,name, the_geom) VALUES ('from web','Shanghai ECNU',-110,43,'this is a string',  ST_SetSRID(ST_Point(-110, 43),4326))",
         api_key: myAPIkey
     };
     $.getJSON(url, function(data) {
         alert(data);
     });
     $('#houseNewRecordDiv').hide();
     ClearHouseNewRecordDiv();
     L.marker([currentLatLng.lat, currentLatLng.lng]).bindPopup(record.houseName).addTo(map);
     hidediv();
 }


 // get record infro
 function GetHouseNewRecordDiv() {

     var houseNameText = $('#houseName');
     var telTetxt = $('#telphone');
     var agentText = $('#agent');
     var addressText = $("#address");
     var houseRecord = {
         houseName: houseNameText.val(),
         telphone: telTetxt.val(),
         agent: agentText.val(),
         address: addressText.val()
     };
     return houseRecord;
 }


 // clear house record div
 function ClearHouseNewRecordDiv() {
     var houseNameText = $('#houseName');
     var telTetxt = $('#telphone');
     var agentText = $('#agent');
     var addressText = $('#address');
     houseNameText.val("");
     telTetxt.val("");
     agentText.val("");
     addressText.val("");
 }
 
 function getALlHouseRecord(){
	 $.ajax({
		 type:'get',
		 url:'../Test',
		 data:{
			 tableName:'house'
		 },
		 success:function(msg){
			 data = JSON.parse(msg);
			 $.each(data,function(id,val){
				 try{
				 L.marker([val.lat, val.lon]).bindPopup(val.name).addTo(map);
				 }catch(e ){
					 
				 }
			 });
			 	
		 },
		 error:function(){
			 alert("sorry, load failed!");
		 }
	 
	 });
 }
/**
 * 添加掩膜
 * @returns
 */
 function showdiv() {
     document.getElementById("background_div").style.display = "block";
     document.getElementById("map").style.display = "block";
 }
 
/**
 * 去除掩膜
 * @returns
 */
 function hidediv() {
     document.getElementById("background_div").style.display = 'none';
     //document.getElementById("map").style.display = 'none';
 }

 function LayerChange() {
     // body...
     $()
     console.log();
 }