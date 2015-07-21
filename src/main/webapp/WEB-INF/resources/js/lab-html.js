var HtmlEditor = function(options){
        var self = this,
            def  = {
                base:true,
                root:'laboratory',
                editor:['JavaScript','HTML','CSS']
            };
        def = $.extend(def,options);

        this.base  = def.base;
        this.editor = {};
        var root    = $(document.getElementById(def.root)),
            tab     = $('<div>').addClass('tabbable').attr('id', 'laboratory-tab').appendTo(root),
            tabNav  = $('<ul>').addClass('nav nav-tabs  clearfix')
                .attr('id', 'moveHead')
                .appendTo(tab),
            tabPane = $('<div>').addClass('tab-content').appendTo(tab);
        for(var i = 0;i<def.editor.length;i++){
            var $tab = $('<li>').attr('id','tab-'+def.editor[i]).appendTo(tabNav),
                $panel = $('<div>').addClass('tab-pane').attr('id', 'pane-'+def.editor[i]).appendTo(tabPane);

            //第一个编辑器默认选中
            if(i==0){
                $tab.addClass('active');
                $panel.addClass('active');
            }
            $tab.append($('<a>').attr({
                href: 'pane-'+def.editor[i],
                'data-toggle': 'tab'
            }).text(def.editor[i]));
            var mode;
            switch (def.editor[i]){
                case 'JavaScript':
                    mode = 'javascript';
                    break;
                case 'HTML':
                    mode = 'text/xml';
                //     mode = {
                //     name: "htmlmixed",
                //     scriptTypes: [{matches: /\/x-handlebars-template|\/x-mustache/i,
                //         mode: null},
                //         {
                //             matches: /(text|application)\/(x-)?vb(a|script)/i,
                //             mode: "vbscript"}]
                // };
                    break;
                case 'CSS':
                    mode = 'text/css';
                    break;
                default:
                    break;
            }
            var editorId = 'editor'+def.editor[i];
            $panel.append($('<textarea>').attr('id',editorId));
            self.editor[def.editor[i]] = CodeMirror.fromTextArea(document.getElementById(editorId), {
                mode: mode,
                lineNumbers: true,
                lineWrapping: true,
                matchBrackets: true,
                foldGutter: true,
                continueComments: "Enter",
                extraKeys: {
                    "Ctrl-Q": function (cm) {
                        cm.foldCode(cm.getCursor());
                    }
                },
                gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
            });
        }
        self.header = $('<div>').appendTo(root);

        self.result = $('<iframe>').attr('id', 'frame').addClass('terminal').appendTo(root);

        self.operate = $('<div>').addClass('neOperate').appendTo(root);
        self.submit = $('<button>')
            .attr('type', 'button')
            .text(' 提交运行')
            .addClass('btn btn-primary submit ')
            .prepend($('<i>').addClass('glyphicon glyphicon-cloud-upload'))
            .appendTo(self.operate).bind('click', function () {
                self.run();
                //外部注册事件
                def.onRun&&def.onRun(self.editor);
            });
        //清空
        self.clear = $('<button>')
            .attr('type', 'button')
            .addClass('btn btn-default reset')
            .text(' 清空代码')
            .prepend($('<i>').addClass('glyphicon glyphicon-trash'))
            .appendTo(self.operate).bind('click', function () {
                self.reset();
            });

        $('li', tabNav).click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            var href = $('a', this).attr('href');
            $('#' + href).addClass('active').siblings().removeClass('active');
            self.editor[href.replace('pane-','')].refresh();
        });
    };
		HtmlEditor.prototype.constructor = HtmlEditor;
		HtmlEditor.prototype.run = function(){
        var base = this.base ? '<base href="'+location.href.replace('class','course')+'/"/>' : '';

        var html      = this.editor['HTML'].getValue().trim(),
            frame     = document.getElementById('frame'),
            headNodes = [];
        if(this.editor['JavaScript']){
        	headNodes.push("<script>" + this.editor['JavaScript'].getValue() + "</scri" + "pt>");
        }
        if(this.editor['CSS']){
        	headNodes.push("<style>" + this.editor['CSS'].getValue()+"</style>");}

        if(/<html[\s\S]*?<\/html>$/.test(html)){
            if(/<head[\s\S]*?<\/head>/.test(html)){
                html = html.replace(/<head>([\s\S]*)<\/head>/,
                    '<head>'+ base +'$1' + headNodes.join('')+'</head>');
            }else{
                html = html.replace(/<html([\s\S]*?)>([\s\S]*?)<\/html>/,
                    '<html$1>\n<head>'+ base + headNodes.join('')+'</head>$2');
            }
        }else{
            html = '<head>' + base + headNodes.join('')+'</head>'+html;
        }
        //html = html.replace(/\/example\//g,this.source);
        var blob = new Blob([html], {type: "text/html"});
        frame.src = URL.createObjectURL(blob);
    };		    
    HtmlEditor.prototype.reset = function(){
        for(var i in this.editor){
            this.editor[i].setValue('');
        }
        this.result.contents().find('html').html('');
    };
    HtmlEditor.prototype.setValue = function(data){
        var def = {
            base:true,
            js: '',
            css: '',
            html: ''
        };
        def = $.extend(def, data);
        this.base = def.base;
        for(var i in this.editor){
            var eName = i=='JavaScript'?'js':i;
            this.editor[i].setValue(def[eName.toLowerCase()])
        }
        if(def.active){
            var tab = def.active == 'js'?'JavaScript':def.active.toUpperCase();
           $('#tab-'+tab).click();

            this.editor[tab]&&this.editor[tab].refresh();
        }
        this.result.contents().find('html').html('');
        this.run();
    };
    HtmlEditor.prototype.write = function(content){
        this.result.contents().find('html').html(content);
    };
    HtmlEditor.prototype.onMessage = function(packet){
        switch (packet.cmd) {
            case 'write':
                this.write(packet.data);
                break;
            case 'setValue':
                this.setValue(packet.data);
                break;
            default :
                break;
        }
    };    
		var editor = new HtmlEditor();