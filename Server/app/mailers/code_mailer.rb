class CodeMailer < ApplicationMailer

  # Subject can be set in your I18n file at config/locales/en.yml
  # with the following lookup:
  #
  #   en.code_mailer.keymail.subject
  #
  def keymail
    @greeting = "Hi"

    mail to: "aravinththangasami@gmail.com" ,subject:  "hello testing"
  end
end
