/*
	Feel free to use your custom icons for the tree. Make sure they are all of the same size.
	If you don't use some keys you can just remove them from this config
*/

var TREE_TPL = {

	// general
	'target':'content',	// name of the frame links will be opened in
							// other possible values are:
							// _blank, _parent, _search, _self and _top

	// icons - root	
	'icon_48':'icons/base.gif',   // root icon normal
	'icon_52':'icons/base.gif',   // root icon selected
	'icon_56':'icons/base.gif',   // root icon opened
	'icon_60':'icons/base.gif',   // root icon selected opened

	// icons - node	
	'icon_16':'icons/empty1.gif', // node icon normal
	'icon_20':'icons/empty1.gif', // node icon selected
	'icon_24':'icons/empty1.gif', // node icon opened
	'icon_28':'icons/empty1.gif', // node icon selected opened
	
	'icon_80':'icons/folder.gif', // mouseovered node icon normal

	// icons - leaf
	'icon_0':'icons/page.gif', // leaf icon normal
	'icon_4':'icons/page.gif', // leaf icon selected

	// icons - junctions	
	'icon_2':'icons/joinbottom.gif', // junction for leaf
	'icon_3':'icons/join.gif',       // junction for last leaf
	'icon_18':'icons/plusbottom.gif', // junction for closed node
	'icon_19':'icons/plus.gif',       // junctioin for last closed node
	'icon_26':'icons/minusbottom.gif',// junction for opened node
	'icon_27':'icons/minus.gif',      // junctioin for last opended node

	// icons - misc
	'icon_e':'icons/empty.gif', // empty image
	'icon_l':'icons/line.gif',  // vertical line
	
	// styles - root
	'style_48':'treenormal', // normal root caption style
	'style_52':'treenormal', // selected root catption style
	'style_56':'treenormal', // opened root catption style
	'style_60':'treenormal', // selected opened root catption style
	'style_112':'treenormal', // mouseovered normal root caption style
	'style_116':'treenormal', // mouseovered selected root catption style
	'style_120':'treenormal', // mouseovered opened root catption style
	'style_124':'treenormal', // mouseovered selected opened root catption style
	
	// styles - node
	'style_16':'treenormal', // normal node caption style
	'style_20':'treenormal', // selected node catption style
	'style_24':'treenormal', // opened node catption style
	'style_28':'treenormal', // selected opened node catption style
	'style_80':'treenormal', // mouseovered normal node caption style
	'style_84':'treenormal', // mouseovered selected node catption style
	'style_88':'treenormal', // mouseovered opened node catption style
	'style_92':'treenormal', // mouseovered selected opened node catption style

	// styles - leaf
	'style_0':'treenormal', // normal leaf caption style
	'style_4':'treeopened', // selected leaf catption style
	'style_64':'treenormal', // mouseovered normal leaf caption style
	'style_68':'treeopened', // mouseovered selected leaf catption style
	
	'onItemMover':'returnFalse',
	'onItemMout':'returnFalse'
	
	// make sure there is no comma after the last key-value pair
};
	
function returnFalse() {
	return false;
}