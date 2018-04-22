/* <![CDATA[ */
  jQuery(table.jqId).find('th .ui-column-filter[onenter]').each(function(idx) {
  var curEl = jQuery(this);
  curEl.unbind('keydown');
 
  curEl.keydown(function(event) {
    var e = (window.event) ? window.event : event;
    if(e.keyCode == 13) {
      event.preventDefault();
      eval(curEl.attr('onenter'));
    }
  })
});
/* ]]> */
