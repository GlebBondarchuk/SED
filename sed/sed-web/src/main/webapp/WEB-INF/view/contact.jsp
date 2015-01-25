<!-- Page Heading/Breadcrumbs -->
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Contact
            <small>Subheading</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${applicationPath}/">Home</a>
            </li>
            <li class="active">Contact</li>
        </ol>
    </div>
</div>
<!-- /.row -->

<!-- Content Row -->
<div class="row">
    <!-- Map Column -->
    <div class="col-md-8">
        <!-- Embedded Google Map -->
        <iframe width="100%" height="400px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2351.1269220140075!2d27.
                547007999999995!3d53.893947999999995!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcf9b2cfa2ddf%3A0x61bdf8323afbfd08
                !2z0JHQtdC70L7RgNGD0YHRgdC60LjQuSDQs9C-0YHRg9C00LDRgNGB0YLQstC10L3QvdGL0Lkg0YPQvdC40LLQtdGA0YHQuNGC0LXRgg!5e0!3m2!1sru!2s!4v1422195021598">

        </iframe>
    </div>
    <!-- Contact Details Column -->
    <div class="col-md-4">
        <h3>Contact Details</h3>

        <p>
            3481 Melrose Place<br>Beverly Hills, CA 90210<br>
        </p>

        <p><i class="fa fa-phone"></i>
            <abbr title="Phone">P</abbr>: (123) 456-7890</p>

        <p><i class="fa fa-envelope-o"></i>
            <abbr title="Email">E</abbr>: <a href="mailto:name@example.com">name@example.com</a>
        </p>

        <p><i class="fa fa-clock-o"></i>
            <abbr title="Hours">H</abbr>: Monday - Friday: 9:00 AM to 5:00 PM</p>
        <ul class="list-unstyled list-inline list-social-icons">
            <li>
                <a href="#"><i class="fa fa-facebook-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-linkedin-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-twitter-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-google-plus-square fa-2x"></i></a>
            </li>
        </ul>
    </div>
</div>
<!-- /.row -->

<!-- Contact Form -->
<!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
<div class="row">
    <div class="col-md-8">
        <h3>Send us a Message</h3>

        <form name="sentMessage" id="contactForm" novalidate>
            <div class="control-group form-group">
                <div class="controls">
                    <label>Full Name:</label>
                    <input type="text" class="form-control" id="name" required data-validation-required-message="Please enter your name.">

                    <p class="help-block"></p>
                </div>
            </div>
            <div class="control-group form-group">
                <div class="controls">
                    <label>Phone Number:</label>
                    <input type="tel" class="form-control" id="phone" required data-validation-required-message="Please enter your phone number.">
                </div>
            </div>
            <div class="control-group form-group">
                <div class="controls">
                    <label>Email Address:</label>
                    <input type="email" class="form-control" id="email" required data-validation-required-message="Please enter your email address.">
                </div>
            </div>
            <div class="control-group form-group">
                <div class="controls">
                    <label>Message:</label>
                    <textarea rows="10" cols="100" class="form-control" id="message" required
                              data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
                </div>
            </div>
            <div id="success"></div>
            <!-- For success/fail messages -->
            <button type="submit" class="btn btn-primary">Send Message</button>
        </form>
    </div>

</div>
<!-- /.row -->