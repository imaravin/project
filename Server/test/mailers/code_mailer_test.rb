require 'test_helper'

class CodeMailerTest < ActionMailer::TestCase
  test "keymail" do
    mail = CodeMailer.keymail
    assert_equal "Keymail", mail.subject
    assert_equal ["to@example.org"], mail.to
    assert_equal ["from@example.com"], mail.from
    assert_match "Hi", mail.body.encoded
  end

end
