package com.github.payments.service.impl;

import com.github.payments.service.IBillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BillsService implements IBillsService {



}
