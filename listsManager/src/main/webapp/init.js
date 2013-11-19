$(function() {
	var $container = $('#container');
	$container.isotope({
		itemSelector : '.element',
		filter : '*',
		getSortData : {
			symbol : function($elem) {
				return $elem.attr('data-symbol');
			}
		},
		sortBy : 'symbol'
	});
	$container.toggleClass('variable-sizes').isotope('reLayout');
});