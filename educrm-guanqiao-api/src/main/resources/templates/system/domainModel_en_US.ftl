
<div class="modal-dialog lg-modal">
    <div class="modal-content" >
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Campus information
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" method="POST" method="post" action="/system/domain/save"
                  data-target="/system/domain/query">
                <input type="hidden" name="domin.pkDomain" value="${(domains.pkDomain)!}">
                <input type="hidden" name="domin.creator" value="${(domains.creator)!}">
                <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Advanced campus</label>
                            <div class="col-xs-9 col-md-9">
                                <#if parentDomains??>
                                <select name="domin.pkParent">
                                    <#list parentDomains as par>
                                    <option value="${(par.pkDomain)!}">${(par.caption)!}</option>
                                    </#list>
                                </select>
                                    <#else >
                                        <input  readonly  value="${(parentDomain.caption)!}" class="form-control">
                                        <input type="hidden" readonly name="domin.pkParent" value="${(parentDomain.pkDomain)!}">
                                </#if>
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Campus name</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.caption" class="form-control" value="${(domains.caption)!}" data-bv-notempty data-bv-notempty-message="Please enter the campus name" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">sort</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.sort"  class="form-control" value="${(domains.sort)!}" title="" type="number">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Zip code</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.zip"  class="form-control" value="${(domains.zip)!}" maxlength="6" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Registered telephone</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.invoicePhone"  class="form-control" value="${(domains.invoicePhone)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Registered address</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.invoiceAddress"  class="form-control" value="${(domains.invoiceAddress)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                    </div>
                    <div class="col-xs-4">
                        <div class="form-group">

                            <label class="control-label col-xs-3 col-md-3">type</label>
                            <div class="col-xs-9 col-md-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="domin.kind" type="radio" id="radio1" value="${(domains.kind)!}" <#if domains.kind?? && domains.kind ==2><#else >checked="checked"</#if>>
                                        <label for="radio1">Branch Campus</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="domin.kind" type="radio" id="radio2" value="${(domains.kind)!}" <#if domains.kind?? && domains.kind ==2>checked="checked"</#if>>
                                        <label for="radio2">General school</label>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Campus abbreviation</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.shortCaption"  class="form-control" value="${(domains.shortCaption)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>


                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Telephone</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.phone"  class="form-control" value="${(domains.phone)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>



                        <div class="form-group ">
                            <label class="control-label  col-xs-3 col-md-3">mailbox</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.email"  class="form-control" value="${(domains.email)!}" title="" type="email">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">issuing office</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.invoiceUnit" class="form-control" value="${(domains.invoiceUnit)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">bank account </label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.invoiceBank" class="form-control" value="${(domains.invoiceBank)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>





                    </div>
                    <div class="col-xs-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Is enabled</label>
                            <div class="col-xs-9 col-md-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="domin.isvalid" type="radio" id="radio3" value="${(domains.isvalid)!}" <#if domains.isvalid?? && domains.isvalid ==2><#else >checked="checked"</#if>>
                                        <label for="radio3">是</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="domin.isvalid" type="radio" id="radio4" value="${(domains.isvalid)!}" <#if domains.isvalid?? && domains.isvalid ==2>checked="checked"</#if>>
                                        <label for="radio4">否</label>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Person in charge</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.person"  class="form-control" value="${(domains.person)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">address</label>
                            <div class="col-lg-9">
                                <input name="domin.address"  class="form-control" value="${(domains.address)!}" title="" type="text">
                            </div>
                        </div><!-- /.col -->
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Fax</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.fax" class="form-control" value="${(domains.fax)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Tax number</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="domin.invoiceCode" class="form-control" value="${(domains.invoiceCode)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Bank account</label>
                            <div class="col-xs-9 col-md-9">
                                <input  name="domin.invoiceBankAccount"  class="form-control" value="${(domains.invoiceBankAccount)!}" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">Remarks</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea  rows="4" name="domin.memo" value="${(domains.memo)!}" class="form-control"  title=""></textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group ">
                            <label class="control-label new-left col-xs-3">Founder</label>
                            <div class="col-xs-9 new-right">
                                <input readonly class="form-control" value="${(domains.map.creatorEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Creation time</label>
                            <div class="col-xs-8">
                                <input readonly class="form-control" value="${(domains.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group ">
                            <label class="control-label col-xs-3">Modifier</label>
                            <div class="col-xs-9">
                                <input readonly class="form-control" value="${(domains.map.modifierEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-3">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Modified</label>
                            <div class="col-xs-8">
                                <input readonly class="form-control" value="${(domains.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-newblue btn-sm">Determine</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function(data) {

            if(data.code==0){
                Notify.success(data.message);
                if($form.data('target')){
                    setTimeout("window.location.href='"+$form.data('target')+"'",3000);
                }
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>